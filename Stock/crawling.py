# DB 연결
import pymysql
conn = pymysql.connect(
  # private key
)

curs = conn.cursor()

from bs4 import BeautifulSoup
import requests
import pandas as pd
from datetime import datetime

def get_price(company_code):
    url = 'https://finance.naver.com/item/main.nhn?code=' + stock_to_code[key]
    source_code = requests.get(url)
    bs_obj = BeautifulSoup(source_code.content, "html.parser")
    no_today = bs_obj.find("p", {"class" : "no_today"})
    blind = no_today.find("span", {"class": "blind"})
    now_price = blind.text
    return now_price

def get_rate(company_code):
    url = 'https://finance.naver.com/item/sise.nhn?code=' + stock_to_code[key]
    source_code = requests.get(url)
    bs_obj = BeautifulSoup(source_code.content, "html.parser")
    no_exday = bs_obj.select_one('#_rate > span')
    now_rate = no_exday.text
    return now_rate.strip()

dfstockcode = pd.read_html('http://kind.krx.co.kr/corpgeneral/corpList.do?method=download',
                           header=0)[0]
print(dfstockcode)
print(dfstockcode.info())
print(dfstockcode.isnull().sum())

dfstockcode.종목코드 = dfstockcode.종목코드.map('{:06d}'.format)
print(dfstockcode)

stock_to_code = dict(zip(dfstockcode.회사명, dfstockcode.종목코드))
print(stock_to_code)

df = pd.DataFrame(columns=['종목코드', '종목명', '시간', '가격', '등락률'])
for key in stock_to_code:
    now = datetime.now()
    #print(now)

    now_price = get_price(stock_to_code[key])
    now_rate = get_rate(stock_to_code[key])
    df = df.append(pd.DataFrame([[stock_to_code[key], key, now, now_price, now_rate]],
                                columns=['종목코드', '종목명', '시간', '가격', '등락률']), ignore_index=True)
    print(stock_to_code[key], key, now, now_price, now_rate)
    curs.execute(f"INSERT INTO stock VALUES(\"{stock_to_code[key]}\",\"{key}\",\"{now}\",\"{now_price}\",\"{now_rate}\")")
    conn.commit()

df.set_index('stock_to_code[key]', inplace=True)
print(df)

