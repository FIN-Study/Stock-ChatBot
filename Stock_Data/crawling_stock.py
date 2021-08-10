#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import pandas as pd
from datetime import datetime
import asyncio

# 동기식 모델
# 장점: 설계가 매우 간단하고 직관적
# 단점: 요청에 따른 결과가 반환되기 전까지 아무것도 못하고 대기

# 비동기식 모델
# 장점: 요청에 따른 결과가 반환되는 시간 동안 다른 작업을 수행할 수 있음
# 단점: 동기식보다 설계가 복잡하고 논증적

# DB 연결
import pymysql

conn = pymysql.connect(
    user='stockbotAdmin',
    passwd='a40844084',
    host='stock-chatbot-rds.cgw5ybwbhaw9.ap-northeast-2.rds.amazonaws.com',
    db='stock_chatbot',
    charset='utf8'
)

curs = conn.cursor()


# 비동기로 실행
async def get_price(company_code):
    url = 'https://finance.naver.com/item/main.nhn?code=' + company_code

    # 현재의 이벤트 루프 가져오기
    loop = asyncio.get_event_loop()
    # 지정된 실행기에서 requests.get이 호출되도록 배치
    # async를 붙여 비동기 함수 선언, await로 비동기 작업을 대기
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
        curs.execute(sql_update)
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

    # 새 이벤트 루프를 만들어 현재 이벤트 루프로 설정
    loop = asyncio.get_event_loop()
    # main()이 완료될 때까지 실행
    loop.run_until_complete(main())
    # 이벤트 루프 닫기
    loop.close()
