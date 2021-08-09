# DB 연결
import pymysql

conn = pymysql.connect(
  # private key
)

curs = conn.cursor()
print(type(curs))

sql = '''
CREATE TABLE stock (
종목코드 varchar(255),
종목명 varchar(255),
시간 DATETIME,
가격 varchar(255),
등락률 varchar(255))
'''
curs.execute(sql)
conn.commit()
conn.close()
