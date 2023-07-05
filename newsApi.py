import mysql.connector
import os
import csv
from dotenv import load_dotenv
from newsapi import NewsApiClient
from datetime import datetime

load_dotenv()

MYSQL_USERNAME = os.getenv('MYSQL_USERNAME')
MYSQL_PASSWORD = os.getenv('MYSQL_PASSWORD')
MYSQL_HOSTNAME = os.getenv('MYSQL_HOSTNAME')
MYSQL_DATABASE = os.getenv('MYSQL_DATABASE')

# Init
newsapi = NewsApiClient(api_key='8639a735e2a24a87be184b8255c8b32f')

# # /v2/everything
# all_articles = newsapi.get_everything(q='bitcoin',
#                                       sources='bbc-news,the-verge',
#                                       domains='bbc.co.uk,techcrunch.com',
#                                       from_param='2023-05-25',
#                                       to='2023-05-25',
#                                       language='en',
#                                       sort_by='relevancy',
#                                       page=2)

newsapi_response = newsapi.get_top_headlines(sources='bbc-news')
all_articles = newsapi_response['articles']

cnx = mysql.connector.connect(user=MYSQL_USERNAME, password=MYSQL_PASSWORD,
                              host=MYSQL_HOSTNAME,
                              database=MYSQL_DATABASE,
                              auth_plugin='mysql_native_password')

mycursor = cnx.cursor()

sql = "INSERT INTO news (title, author, description, url, url_to_image, published_date_time, content) VALUES (%s, %s, %s, %s, %s, %s, %s)"
for article in all_articles:
    publishedAt = datetime.strptime(article["publishedAt"][:-2], "%Y-%m-%dT%H:%M:%S.%f")
    val = [ (article['title'], article['author'], article['description'], article['url'], article['urlToImage'], publishedAt, article['content']) ]

    mycursor.executemany(sql, val)

    cnx.commit()


cnx.close()

with open("news.csv", 'a') as file:
    writer = csv.writer(file)

    for article in all_articles:
        data = [article['title'], article['author'], article['description'], article['url'], article['urlToImage'], article["publishedAt"], article['content']]
        writer.writerow(data)

        data.rem

    file.close()