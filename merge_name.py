import pickle
import pandas as pd
import re
from datetime import datetime
from fuzzywuzzy import process, fuzz
import concurrent.futures
from functools import partial
from collections import defaultdict
from tqdm import tqdm

# 去除姓名中多余的引号，避免干扰
def removeRedundantMarks():
    print("开始去除多余引号...")
    raw_data = pd.read_csv('./origin_data_full.csv', encoding='utf-8', low_memory=False)
    for column in ['Actors', 'Directors']:
        raw_data[column] = raw_data[column].str.replace('"', '')
    raw_data.to_csv('./output_cleaned.csv', index=False, encoding='utf-8')
    print("去除多余引号完成...")

# 提取所有姓名到set中，并存储到二进制文件
def beforeMerge():
    print("开始提取所有姓名...")
    raw_data = pd.read_csv('./output_cleaned.csv', encoding='utf-8', low_memory=False)
    name_set = set()

    for column in ['Directors', 'Actors']:
        raw_data[column].dropna(inplace=True)
        for index, names in enumerate(raw_data[column]):
            if pd.notnull(names):
                for name in names.split(','):
                    name = name.strip()
                    if name and not re.match(r'^[\s#*\-]+$', name):  # 过滤无效字符串
                        name_set.add(name)
            if (index + 1) % 10000 == 0:
                print(f"已处理 {index + 1} 行数据...")

    with open('set.pkl', 'wb') as set_file:
        pickle.dump(name_set, set_file)

    name_mappings = defaultdict(str)
    with open('map.pkl', 'wb') as map_file:
        pickle.dump(name_mappings, map_file)
    print("提取所有姓名完成...")

# 并行处理姓名合并
def mergeNamesParallel(names_chunk, name_set, name_mappings):
    for index, name in enumerate(tqdm(names_chunk, desc="处理姓名")):
        if name:  # 确保名称非空
            similar_names = process.extractBests(name, name_set, score_cutoff=90, scorer=fuzz.ratio)
            for similar_name, score in similar_names:
                if similar_name != name:
                    name_set.discard(similar_name)
                    name_mappings[similar_name] = name
    return name_set, name_mappings

def mergeNames():
    print("开始合并姓名...")
    with open('set.pkl', 'rb') as set_file:
        name_set = pickle.load(set_file)
    with open('map.pkl', 'rb') as map_file:
        name_mappings = pickle.load(map_file)

    name_list = sorted(list(name_set))
    chunk_size = len(name_list) // 10  # 根据CPU核心数调整分块数量
    chunks = [name_list[i:i + chunk_size] for i in range(0, len(name_list), chunk_size)]

    with concurrent.futures.ProcessPoolExecutor() as executor:
        func = partial(mergeNamesParallel, name_set=name_set.copy(), name_mappings=name_mappings.copy())
        results = list(tqdm(executor.map(func, chunks), total=len(chunks), desc="处理姓名分块"))

    # 合并结果
    for name_set_part, name_mappings_part in results:
        name_set.intersection_update(name_set_part)
        name_mappings.update(name_mappings_part)

    print(f"合并姓名完成，共 {len(name_mappings)} 个映射关系...")
    with open('set.pkl', 'wb') as set_file:
        pickle.dump(name_set, set_file)
    with open('map.pkl', 'wb') as map_file:
        pickle.dump(name_mappings, map_file)

# 根据映射关系替换导演、演员、主演的姓名
def afterMerge():
    print("开始根据映射关系替换姓名...")
    raw_data = pd.read_csv('./output_cleaned.csv', encoding='utf-8', low_memory=False)
    with open('map.pkl', 'rb') as map_file:
        name_mappings = pickle.load(map_file)

    columns_to_replace = ['Directors', 'Actors']
    for column in columns_to_replace:
        raw_data[column] = raw_data[column].apply(
            lambda x: ', '.join(
                [name_mappings.get(name.strip(), name.strip()) for name in str(x).split(',') if name.strip()]
            ) if pd.notnull(x) else x
        )
        print(f"已处理列 {column}...")

    raw_data = raw_data.replace({'': None, 'nan': None})
    raw_data.to_csv('./final_movie_info.csv', index=False, encoding='utf-8')
    print("根据映射关系替换姓名完成...")

if __name__ == "__main__":
    removeRedundantMarks()
    beforeMerge()
    mergeNames()
    afterMerge()