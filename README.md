# seckill
srping boot + Mybatis + redis 商品秒杀


本文主要借鉴网上一些通用的做法，做出一个例子，主要实现了一个抢购接口:
http://localhost:8080/seckill/product/1?userId=1

seckill.sql文件为建表sql
pro_insert.sql文件为success_killed表中数据添加10000条用户预约记录
seckill_insert.sql文件为seckill生成一条产品信息

具体验证逻辑是执行test文件夹下的两个test类：
RemoteInvote.java
RemoteInvote2.java
这两个test类没有什么差别，主要是为了增加并发量