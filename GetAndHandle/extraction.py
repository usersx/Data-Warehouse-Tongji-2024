# 涉及的页面：
# 1. 白底商品页面
# 2. 黑底电影页面（Amazon prime movies）
# 3. 其他页面（狗图、乱码、验证码、空，不做处理）
# 
# 黑底页面全部处理，其他页面均不处理
# 白底页面需要先筛选，筛出除 Movies&TV 以外的页面（如 CD）

from lxml import etree
from concurrent.futures import ThreadPoolExecutor
import os
import csv

MAX_WORKERS = 180
RESOURCE_DIR = 'data'
# OUTPUT_FILE = 'data.csv'
OUTPUT_DIR = 'csvs'

class MovieInfo:
    def __init__(self, asin):
        self.asin = asin       # 电影 ID
        self.title = ''        # 电影名
        self.language = ''     # 电影语言
        self.release_date = '' # 上映日期
        self.rated = ''        # 电影评级
        self.description = ''  # 电影描述
        self.actors = ''       # 电影演员
        self.director = ''     # 电影导演
        self.genres = ''       # 电影风格
        self.imdb = ''         # IMDb 评分
        self.editions = []     # 电影版本（媒体格式）
        self.related_ids = set() # 相关 ID

    def tovec(self):
        return [
            self.asin,
            self.title,
            # subtitle
            self.language,
            # release_year
            self.release_date,
            self.rated,
            # run time
            self.description,
            # contributor
            self.actors,
            self.director,
            # producers
            # medie format -> edition
            self.genres,
            # 以下两个都是评分
            # customers_reviews
            self.imdb,

            # number_of_discs
            # studio
            # main_actor -> actors
            ','.join(self.editions),
        ]

    def isMovie(self):
        counter = 0
        if self.release_date == '':
            counter += 1
        if self.actors == '':
            counter += 1
        if self.director == '':
            counter += 1
        if self.genres == '':
            counter += 1
        if self.language == '':
            counter += 1
        if self.rated == '':
            counter += 1
        return 6 - counter > 2

class Parser:
    def __init__(self, target_dir):
        self.target_dir = target_dir
        if not os.path.exists(target_dir):
            os.mkdir(target_dir)

    def parse(self, file_path, file_name):
        with open(os.path.join(file_path, file_name), 'r', encoding='utf-8') as f:
            content = f.read()

        root = etree.HTML(content)

        # Movies & TV
        # Prime Video
        page_label = root.xpath('normalize-space(//*[@id="wayfinding-breadcrumbs_feature_div"]/ul/li[1]/span/a/text())')
        if 'Movies & TV' in page_label:
            print(f"{file_name} Movie!")
            self.parseGeneralPage(root, file_name.split('.')[0])
        page_label = root.xpath('normalize-space(//div[@id="pv-nav-container"]/div/a/img/@alt)')
        if 'Prime Video' in page_label:
            print(f"{file_name} Prime")
            self.parsePrimePage(root, file_name.split('.')[0])
        else:
            print(f"{file_name} other!")

    def parseGeneralPage(self, page, asin):
        info = MovieInfo(asin)

        # 获取电影标题
        info.title = page.xpath('normalize-space(//span[@id="productTitle"]/text())')

        # 获取电影版本
        movie_edition = page.xpath('normalize-space(//*[@id="declarative_"]/table/tbody/tr/td[2]/div/span/text())')
        if movie_edition and movie_edition != '':
            info.editions.append(movie_edition)

        # 获取电影评级
        rated = page.xpath('//*[@id="bylineInfo"]/div/div/span[@class="a-size-small"]/text()')
        if rated == None or len(rated) == 0:
            rated = page.xpath('//*[@id="bylineInfo_feature_div"]/div/div/span[@class="a-size-small"]/text()')
        if len(rated) > 0:
            info.rated = rated[0]
        # 获取 IMDb 评分
        imdb = page.xpath('//span[@class="imdb-rating"]/strong/text()')
        if len(imdb) > 0:
            info.imdb = imdb[0]
        # 获取电影描述
        description = page.xpath('//div[@id="productDescription_fullView"]/div/p/span/text()')
        if len(description) > 0:
            info.description = description[0]

        # 获取电影风格并补充定影版本信息
        tbody_elements = page.xpath('//*[@id="productOverview_feature_div"]/div/table/tbody')  # 获取tbody元素
        if tbody_elements:
            tr_elements = tbody_elements[0].xpath('.//tr')  # 获取所有行
            for tr_element in tr_elements:  # 处理每一行中的所有列
                element1 = tr_element.xpath('normalize-space(.//td[1]/span/text())')
                element2 = tr_element.xpath('normalize-space(.//td[2]/span/text())')
                if element1 == 'Genre':
                    info.genres = element2
                elif element1 == 'Language':
                    info.language = element2

        # 获取details并抽取相关信息
        movie_starring = ''
        movie_details = page.xpath('//*[@id="detailBullets_feature_div"]/ul/li/span')
        for detail in movie_details:
            key = detail.xpath('normalize-space(.//span[1]/text())')
            value = detail.xpath('normalize-space(.//span[2]/text())')
            if 'Release date' in key:
                info.release_date = value
            elif 'Starring' in key:
                movie_starring = value
            elif 'Actors' in key:
                info.actors = value
            elif 'Director' in key:
                info.director = value
            elif 'Format' in key:
                info.editions.append(value)

        # 获取同一部电影的不同id
        hrefs = page.xpath('//*[@id="tmmSwatches"]/ul/li//a[contains(@href, "/dp/")]/@href')
        for href in hrefs:
            info.related_ids.add(href.split('/dp/')[1][:10])

        # 缺失数据过多则可能不是电影
        if info.isMovie():
            with open(os.path.join(self.target_dir, asin), 'a', encoding='utf-8', newline='') as csv_file:
            # with open(self.target_file, 'a', encoding='utf-8', newline='') as csv_file:
                writer = csv.writer(csv_file)
                writer.writerow(info.tovec())

    def parsePrimePage(self, page, asin):
        info = MovieInfo(asin)

        # 获取电影标题
        info.title = page.xpath('normalize-space(//*[@data-automation-id="title"]/text())')

        # 获取电影评级
        rated = page.xpath('//span[@data-automation-id="rating-badge"]/text()')
        if len(rated) > 0:
            info.rated = rated[0]
        # 获取 IMDb 评分
        imdb = page.xpath('//span[@data-automation-id="imdb-rating-badge"]/@aria-label')
        if (imdb != None and len(imdb) > 0):
            parts = imdb[0].split("imdb Rating ")
            if len(parts) > 1:
                self.imdb = parts[1]
        # 获取电影描述
        description = page.xpath('//*[@id="main"]/div[1]/div/div/div[2]/div[3]/div/div[2]/div[2]/span/span/text()')
        if len(description) > 0:
            info.description = description[0]

        # 获取电影详细信息
        movie_details = page.xpath('//*[@id="btf-product-details"]/div[1]/dl')
        movie_starring = ''
        movie_language = ''
        for movie_info in movie_details:
            key = movie_info.xpath('.//dt/span/text()')
            values = movie_info.xpath('.//dd/text()')
            if not values:
                values = movie_info.xpath('.//dd/a/text()')
            if 'language' in ','.join(key).lower():
                info.language = ','.join(values)
            elif 'directors' in ','.join(key).lower():
                info.director = ','.join(values)
            elif 'starring' in ','.join(key).lower():
                movie_starring = ','.join(values)
            elif 'actor' in ','.join(key).lower():
                info.actors = ','.join(values)

        # 获取电影上映时间
        info.release_date = page.xpath(
            'normalize-space(//*[@data-automation-id="release-year-badge"]/text())')

        # 获取电影风格
        genre_span = page.xpath('//*[@data-testid="genresMetadata"]/span/text()')
        genre_a = page.xpath('//*[@data-testid="genresMetadata"]/span/a/text()')
        genre_metadata = [genre for genre in (genre_a + genre_span) if genre != '·']
        info.genres = ','.join(genre_metadata)

        # 获取同一部电影的不同id
        hrefs = page.xpath('//*[@id="btf-product-details"]/div//a[contains(@href, "/dp/")]/@href')
        for href in hrefs:
            id = href.split('/dp/')[1][:10]
            info.related_ids.add(id)

        movie_edition = 'prime video'
        with open(os.path.join(self.target_dir, asin), 'a', encoding='utf-8', newline='') as csv_file:
        # with open(self.target_file, 'a', encoding='utf-8', newline='') as csv_file:
            writer = csv.writer(csv_file)
            writer.writerow(info.tovec())

def main():
    parser = Parser(OUTPUT_DIR)
    with ThreadPoolExecutor(max_workers=MAX_WORKERS) as executor:
        for file_name in os.listdir(RESOURCE_DIR):
            executor.submit(parser.parse, RESOURCE_DIR, file_name)

if __name__ == '__main__':
    main()
