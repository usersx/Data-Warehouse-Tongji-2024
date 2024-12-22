import datetime
import pandas as pd

ASIN = 0
RELEASE_DATE = 4

def addExtraTime():
    output = pd.read_csv("./final_movie_info.csv", encoding="utf-8")

    review_time = pd.read_csv("./review_time.csv", encoding="utf-8")

    review_time_dict = review_time.set_index("productID")["reviewTime"].to_dict()

    def convert_timestamp_to_date(timestamp):
        return datetime.datetime.utcfromtimestamp(timestamp).strftime("%Y-%m-%d")

    for index, row in output.iterrows():
        if pd.isnull(row["Release date"]):
            product_id = row["ASIN"]
            if product_id in review_time_dict:
                review_time = review_time_dict[product_id]
                formatted_date = convert_timestamp_to_date(review_time)
                output.at[index, "Release date"] = formatted_date

    output.to_csv("./output_with_time.csv", index=False, encoding="utf-8")

addExtraTime()
