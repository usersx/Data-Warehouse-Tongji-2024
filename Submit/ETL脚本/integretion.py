import os
import shutil

ORIGIN_DIR = "retries"
NEW_DIR = "retries"
TARGET_DIR = "data"

# 当前已爬数据，可以设置地小一些，防止影响数据爬取
# MAX_SIZE = 5000

def move_files(source_dir1, source_dir2, target_dir, filename):
    if not os.path.exists(target_dir):
        os.mkdir(target_dir)

    source_path = os.path.join(source_dir1, filename)
    target_path = os.path.join(target_dir, filename)

    source_path = source_path if os.path.exists(source_path) else os.path.join(source_dir2, filename)

    if os.path.exists(source_path):
        try:
            shutil.move(source_path, target_path)
        except Exception as e:
            print(f"Error moving '{filename}': {e}")

def main():
    with open("result.txt", "r") as ids_file:
        ids = ids_file.readlines()#[:5000]

    for id in ids:
        id = id.strip()
        print(f"-> {id}")
        move_files(ORIGIN_DIR, NEW_DIR, TARGET_DIR, id + ".html")

if __name__ == '__main__':
    main()
