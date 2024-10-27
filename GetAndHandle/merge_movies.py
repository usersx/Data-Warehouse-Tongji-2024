import pandas as pd
import numpy as np
import re
import pickle


'''
清除表中所有的引号
'''
def removeRedundantMarks():
    print("开始去除多余引号...")

    # 读取 CSV 文件
    raw_data = pd.read_csv('./output_with_time.csv', encoding='utf-8')

    # 清除表中所有的双引号，确保处理的是字符串类型的列
    for column in raw_data.columns:
        if raw_data[column].dtype == object:  # 通常 object 类型包含字符串
            raw_data[column] = raw_data[column].str.replace('\"', '')

    # 将处理后的数据保存到新文件
    raw_data.to_csv('./movie_info_1.csv', index=False, encoding='utf-8')

    print("去除多余引号完成...")


'''
去除电影标题中包含的版本信息
便于更好的去重
'''
def removeVersionFromTitle():
    print("开始去除电影版本信息...")
    raw_data = pd.read_csv('./movie_info_1.csv', encoding='utf-8')

    def remove_version_info(title):
        if not isinstance(title, str):
            return title
        
        # 1. 移除所有成对的 [] 和 () 及其内部的内容
        title = re.sub(r'\s*[\[(].*?[\])]\s*', ' ', title)
        
        # 2. 移除仅有开始括号的情况，例如 "(内容" 或 "[内容"
        title = re.sub(r'\s*[\[(].*$', ' ', title)
        
        # 3. 移除所有通过 / 分隔的内容
        title = re.sub(r'/[^/]+', '', title).strip()
        
        # 4. 替换多个空格为一个空格
        title = re.sub(r'\s+', ' ', title)
        
        return title.strip()

    # 应用 remove_version_info 函数来清理电影名
    raw_data['Title'] = raw_data['Title'].apply(remove_version_info)
    
    # 将处理后的数据保存到新文件
    raw_data.to_csv('./movie_info_2.csv', index=False, encoding='utf-8')
    print("去除电影版本信息完成...")


'''
进行电影信息去重
电影名称和导演相同认定为同一部电影
建立数据血缘关系
'''
def merge_movies():
    print("开始进行电影信息去重和数据血缘关系建立...")
    
    # 读取CSV文件
    df = pd.read_csv('./movie_info_2.csv')

    # 1.删除大概率错误数据
    df = df.dropna(subset=['Title', 'ASIN'])

    # 2.去除ASIN重的数据
    df.drop_duplicates(subset=['ASIN'], keep='first', inplace=True)

    # 3. 将 Title 转换为小写以便统一
    df['Title'] = df['Title'].str.lower().str.strip()

    # 4. 处理 Directors 列，填充缺失值为 'un'，并转换为小写
    df['Directors'] = df['Directors'].fillna('un').str.lower().str.strip()

    # 5. 记录原始顺序
    df = df.reset_index().rename(columns={'index': 'original_order'})

    # 6. 基于 Title 和 Directors 进行分组，认为同组的为同一部电影
    grouped = df.groupby(['Title', 'Directors'])

    # 7. 创建去重后的数据，保留每组的第一条记录
    deduped_df = grouped.first().reset_index()

    # 8. 建立数据血缘关系
    # 收集每组的所有 ASIN
    mapping_df = grouped['ASIN'].apply(list).reset_index()
    mapping_df = mapping_df.rename(columns={'ASIN': 'ASIN_List'})

    # 将 ASIN 列表转换为字符串（可选）
    mapping_df['ASIN_List'] = mapping_df['ASIN_List'].apply(lambda x: ','.join(x))

    # 创建映射字典，将重复的 ASIN 映射到主 ASIN
    id_mappings = {}
    for _, row in mapping_df.iterrows():
        asin_list = row['ASIN_List'].split(',')
        main_asin = asin_list[0]  # 主 ASIN 为列表中的第一个
        for asin in asin_list[1:]:
            id_mappings[asin] = main_asin

    # 保存映射关系到二进制文件
    with open('id_mappings.pkl', 'wb') as file:
        pickle.dump(id_mappings, file)

    # 9. 排序去重后的数据按照 original_order
    deduped_df = deduped_df.sort_values('original_order')

    # 10. 获取原始列顺序
    original_columns = pd.read_csv('./movie_info_2.csv', nrows=0).columns.tolist()

    # 确保去重后的数据按原始顺序排列，去掉 'original_order'
    deduped_df = deduped_df[original_columns]

    # 保存去重后的数据
    deduped_df.to_csv('movie_info_deduplication.csv', index=False, encoding='utf-8')

    # 保存映射关系
    mapping_df.to_csv('title_directors_mapping.csv', index=False, encoding='utf-8')

    print("电影信息去重和数据血缘关系建立完成。")
    print(f"去重后的数据保存在 './movie_info_deduplication.csv'")
    print(f"数据血缘关系保存在 './title_directors_mapping.csv'")


def main():
    '''
    以下逐步进行操作
    '''
    # removeRedundantMarks()
    # removeVersionFromTitle()
    merge_movies()

if __name__ == '__main__':
    main()
