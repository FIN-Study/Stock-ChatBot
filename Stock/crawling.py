from bs4 import BeautifulSoup
import requests
import pandas as pd
from datetime import datetime
import asyncio
from functools import partial

# DB 연결
import pymysql

conn = pymysql.connect(
    # private key
)

curs = conn.cursor()


# 비동기로 실행
async def get_price(company_code):
    url = 'https://finance.naver.com/item/main.nhn?code=' + company_code

    loop = asyncio.get_event_loop()
    req = await loop.run_in_executor(None, requests.get, url)
    html = req.text
    bs_obj = await loop.run_in_executor(None, BeautifulSoup,
                                        html, 'html.parser')

    no_today = bs_obj.find("p", {"class": "no_today"})
    blind = no_today.find("span", {"class": "blind"})
    now_price = blind.text
    return now_price


async def get_rate(company_code):
    url = 'https://finance.naver.com/item/sise.nhn?code=' + company_code

    loop = asyncio.get_event_loop()
    req = await loop.run_in_executor(None, requests.get, url)
    html = req.text
    bs_obj = await loop.run_in_executor(None, BeautifulSoup,
                                        html, 'lxml')

    no_exday = bs_obj.select_one('#_rate > span')
    now_rate = no_exday.text
    return now_rate.strip()


async def main():
    df = pd.DataFrame(columns=['stockcode', 'stockname', 'time', 'price', 'rate'])
    for stock in stock_to_code:
        # ('DRB동일', '004840'), ('DSR', '155660'),...stock[0] = stockName, stock[1] = stockCode
        now = datetime.now()

        now_price = await asyncio.gather(asyncio.ensure_future(get_price(stock[1])))
        now_rate = await asyncio.gather(asyncio.ensure_future(get_rate(stock[1])))

        df = df.append(pd.DataFrame([[stock[1], stock[0], now, now_price[0], now_rate[0]]],
                                    columns=['stockcode', 'stockname', 'time', 'price', 'rate']), ignore_index=True)
        print(stock[1], stock[0], now, now_price[0], now_rate[0])

        # 최초 실행시
        sql_insert = f"INSERT INTO stock VALUES(\"{stock[1]}\",\"{stock[0]}\",\"{now}\",\"{now_price[0]}\",\"{now_rate[0]}\")"
        # 이후 실행시
        sql_update = f"UPDATE stock SET time=\"{now}\", price=\"{now_price[0]}\", rate=\"{now_rate[0]}\" WHERE stockcode=\"{stock[1]}\""
        curs.execute(sql_insert)
        conn.commit()

    print(df)


if __name__ == "__main__":
    df_stock = pd.read_html('http://kind.krx.co.kr/corpgeneral/corpList.do?method=download',
                            header=0)[0]
    # print(df_stock)
    # print(df_stock.info())
    # print(df_stock.isnull().sum())

    df_stock.종목코드 = df_stock.종목코드.map('{:06d}'.format)
    # print(df_stock)

    stock_to_code = tuple(zip(df_stock.회사명, df_stock.종목코드))
    # print(stock_to_code)

    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())
    loop.close()
