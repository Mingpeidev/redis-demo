# redis-demo
```
redis学习
```

# 主要功能
```
1、springboot
2、mysql数据库
3、Druid数据库连接池
4、redis
```

# 项目url
```
http://localhost:8080/druid/login.html                      druid登录界面

http://localhost:8080/redis/setAndGet                       普通redis实现
http://localhost:8080/redis/setAndGetByService              调用编写的工具类

http://localhost:8080/user/getUser?id=15                    mysql test
http://localhost:8080/user/getUserCache?id=15               mysql初步整合redis
http://localhost:8080/user/getByCache?id=15                 使用spring自带spring-boot-starter-cache整合缓存
http://localhost:8080/user/getExpire?id=15                  自定义序列化方式、超时时间、key生成规则

http://localhost:8080/session/setSession                    redis实现分布式集群配置 实现session共享 设置session
http://localhost:8080/session/getSession                    redis实现分布式集群配置 实现session共享 获取session

http://localhost:8080/ranking/addScore?uid=5&score=100      添加排行数据
http://localhost:8080/ranking/rank?uid=6                    当前id排行
http://localhost:8080/ranking/scoreByRange?start=0&end=-1   增序排名

```