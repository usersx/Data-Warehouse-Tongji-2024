import asyncio
import aiohttp
import random
import time
import os
from fake_useragent import UserAgent

START_POSITION = 102053  # 开始位置
MAX_WORKERS = 5  # 同时运行的线程数
BATCH_SIZE = 20  # 每批处理的ID数量
MAX_RETRIES = 3  # 最大重试次数




# 使用fake_useragent生成随机User-Agent
ua = UserAgent()

def get_random_headers():
    return {
        "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
        "accept-language": "en-US,en;q=0.9",
        "cache-control": "max-age=0",
        "sec-ch-ua": f"\"{ua.chrome}\", \"Chromium\";v=\"{random.randint(90, 110)}\", \"Google Chrome\";v=\"{random.randint(90, 110)}\"",
        "sec-ch-ua-mobile": "?0",
        "sec-ch-ua-platform": f"\"{random.choice(['Windows', 'MacOS', 'Linux'])}\"",
        "sec-fetch-dest": "document",
        "sec-fetch-mode": "navigate",
        "sec-fetch-site": "none",
        "sec-fetch-user": "?1",
        "upgrade-insecure-requests": "1",
        "user-agent": ua.random,
    }

async def update_cookie():
    return f"session-id={int(time.time())}; csm-hit=tb:{random.randint(1000000, 9999999)}+s-{random.randint(1000000, 9999999)}|{int(time.time())}; ubid-main={random.randint(100, 999)}-{random.randint(1000000, 9999999)}-{random.randint(1000000, 9999999)}"

async def fetch_page(session, id, retry_count=0):
    url = f"https://www.amazon.com/dp/{id}"
    headers = get_random_headers()
    headers["cookie"] = await update_cookie()
    
    try:
        async with session.get(url, headers=headers, timeout=20) as response:
            content = await response.text()
            file_path = f'results/{id}.html'
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            
            file_size = os.path.getsize(file_path)
            if (3000 < file_size <= 9000) or "captcha" in content.lower():
                if retry_count < MAX_RETRIES:
                    print(f"检测到反爬机制或验证码，正在重试 {id}（第 {retry_count + 1} 次）")
                    wait_time = random.uniform(10, 20) * (retry_count + 1)
                    await asyncio.sleep(wait_time)
                    return await fetch_page(session, id, retry_count + 1)
                else:
                    print(f"达到最大重试次数，{id} 爬取失败")
                    with open('max_retries.txt', 'a') as f:
                        f.write(f"{id}\n")
            else:
                print(f"已成功接收 {id} 页面")
    except Exception as e:
        with open(f'errors/{id}.err', 'w', encoding='utf-8') as err_f:
            err_f.write(str(e))
        print(f"处理 {id} 时出错: {str(e)}")
        if retry_count < MAX_RETRIES:
            wait_time = random.uniform(10, 20) * (retry_count + 1)
            await asyncio.sleep(wait_time)
            return await fetch_page(session, id, retry_count + 1)
        else:
            with open('max_retries.txt', 'a') as f:
                f.write(f"{id}\n")

async def process_batch(ids):
    async with aiohttp.ClientSession() as session:
        tasks = [fetch_page(session, id.strip()) for id in ids]
        await asyncio.gather(*tasks)
    
    await asyncio.sleep(random.uniform(5, 10))  # 减少批次之间的等待时间


async def main():
    # 创建必要的目录
    os.makedirs('results', exist_ok=True)
    os.makedirs('errors', exist_ok=True)

    # 确保 max_retries.txt 文件存在，如果不存在则创建
    if not os.path.exists('max_retries.txt'):
        open('max_retries.txt', 'a').close()

    with open('ids_no_dup.txt', 'r') as ids_file:
        ids = ids_file.readlines()[START_POSITION:]

    for i in range(0, len(ids), BATCH_SIZE):
        batch = ids[i:i + BATCH_SIZE]
        await process_batch(batch)


if __name__ == "__main__":
    asyncio.run(main())
