# sharding-example

使用sharding-sphere按时间分表
------
首先创建一个库。
CREATE SCHEMA IF NOT EXISTS demo_ds;

这里做的是，一个库，多张表
按照时间分表。一个季度一张表。

表初始化，在Mapper.xml中

这个只是为了测试，学习。

注意：
Sharding-Sphere提供灵活的配置分布式主键生成策略方式。 在分片规则配置模块可配置每个表的主键生成策略，默认使用雪花算法（snowflake）生成64bit的长整型数据。这里为了方便自己重新写了MyTestKeyGenerator。
每次重新执行插入的时候，需要清除一下原有数据，这些方法在mapper中默认都有提供。
