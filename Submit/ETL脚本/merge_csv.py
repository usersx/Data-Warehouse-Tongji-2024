import os

CSV_DIR = 'csvs'

def merge_files(output_path):
    with open(output_path, 'w', encoding='utf-8') as outfile:
        for filename in os.listdir(CSV_DIR):
            with open(os.path.join(CSV_DIR, filename), 'r', encoding='utf-8') as infile:
                outfile.write(infile.readline())

if __name__ == '__main__':
    merge_files('data.csv')
    print("Over")
