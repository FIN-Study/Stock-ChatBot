# DB 연결
import pymysql

conn = pymysql.connect(
  # private key
)

curs = conn.cursor()
print(type(curs))

sql = '''
CREATE TABLE stock (
stockcode varchar(255),
stockname varchar(255),
time DATETIME,
price varchar(255),
rate varchar(255))
'''
curs.execute(sql)
conn.commit()
conn.close()
