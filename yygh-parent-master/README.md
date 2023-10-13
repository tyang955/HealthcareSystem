# 尚医通

# 医院接口模拟系统

版本：V1.0

1、文档介绍
1.1文档说明
该系统用于医院端接口模拟，配合尚医通完成接口调试。

1.2阅读对象
编程人员及测试人员。
2、服务部署
1、找到资源文件夹下面的hospital-manage项目，导入idea
2、导入sql，路径：hospital-manage/资源文件/sql/表结构.sql
3、修改application-dev.yml文件数据库连接
4、启动项目

3、操作说明
3.1 访问项目
浏览器：http://localhost:9999/，如图：
 
3.2 医院设置
我们在尚医通管理后台设置的医院信息配置到“医院接口模拟系统”，如图：
 
配置参数：
医院code：尚医通分配的医院code
签名key：尚医通分配的签名key
统一预约挂号平台基础路径：尚医通接口基础路径
3.3 上传接口
参考《尚医通API接口文档.docx》
医院、科室与排班等 
对应的测试数据在：hospital-manage/资源文件/示例数据

3.4 回调接口
参考《尚医通API接口文档.docx》
预约下单、更新支付状态和取消预约等


尚医通

版本：V1.0
1项目总结
1.1项目功能总结（后台管理系统）
1、医院设置管理
（1）医院设置列表、添加、锁定、删除
（2）医院列表、详情、排班、下线

2、数据管理
（1）数据字典树形显示、导入、导出

3、用户管理
（1）用户列表、查看、锁定
（2）认证用户审批

4、订单管理
（1）订单列表、详情

5、统计管理
（1）预约统计

1.2项目功能总结（前台用户系统）
1、首页数据显示
（1）医院列表

2、医院详情显示
（1）医院科室显示

3、用户登录功能
（1）手机号登录（短信验证码发送）
（2）微信扫描登录

4、用户实名认证

5、就诊人管理
（1）列表、添加、详情、删除

6、预约挂号功能
（1）排班和挂号详情信息
（2）确认挂号信息
（3）生成预约挂号订单
（4）挂号订单支付（微信）
（5）取消预约订单

7、就医提醒功能

1.3项目技术点总结（后端技术）
1、SpringBoot

2、SpringCloud
（1）Nacos注册中心
（2）Feign
（3）GateWay

3、Redis
（1）使用Redis作为缓存
（2）验证码有效时间、支付二维码有效时间

4、MongoDB
（1）使用MongoDB存储 医院相关数据

5、EasyExcel
（1）操作excel表格，进行读和写操作

6、MyBatisPlus

7、RabbitMQ
（1）订单相关操作，发送mq消息

8、Docker
（1）下载镜像 docker pull 
（2）创建容器 docker run

9、阿里云OSS

10、阿里云短信服务

11、微信登录/支付

12、定时任务

1.4项目技术点总结（前端技术）
1、vue
（1）指令

2、Element-ui

3、nuxt

4、npm

5、ECharts

# 模块简绍

hospital-manage：医院接口模拟端（已开发，直接使用）

yygh-parent：根目录，管理子模块：

common：公共模块父节点

common-util：工具类模块，所有模块都可以依赖于它

rabbit-util：rabbitmq业务封装

service-util：service服务的工具包，包含service服务的公共配置类，所有              service模块依赖于它

server-gateway：服务网关

model：实体类模块

service：api接口服务父节点

service-hosp：医院api接口服务

service-cmn：公共api接口服务

service-user：用户api接口服务

service-order：订单api接口服务

service-oss：文件api接口服务

service-sms：短信 api接口服务

service-task：定时任务服务

service-statistics：统计api接口服务

service-client：feign服务调用父节点

service-cmn-client：公共api接口

service-hosp-client：医院api接口

service-order-client：订单api接口

service-user：用户api接口

![1650179424674](C:\Users\86176\AppData\Roaming\Typora\typora-user-images\1650179424674.png)

# 需要开启的信息

 ### 我的redis、mysql、nacos在本地windows下，而我的mongodb、rabbitmq在我的服务器上

```shell
// 开启的信息
#redis设置
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.database= 0
spring.redis.timeout=1800000
spring.redis.lettuce.pool.max-active=20
spring.redis.lettuce.pool.max-wait=-1
#最大阻塞等待时间(负数表示没限制)
spring.redis.lettuce.pool.max-idle=5
spring.redis.lettuce.pool.min-idle=0

# mysql数据库连接
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/yygh_cmn?characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=

 # nacos服务地址
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
 
 #mongodb连接设置
spring.data.mongodb.uri=mongodb://106.14.60.212:27017/yygh_hosp
#返回json的全局时间格式
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8


#rabbitmq地址
spring.rabbitmq.host=106.14.60.212
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest


#尚硅谷的微信回传地址，进行打开微信二维码的端口为本地回传8160
wx.open.app_id=wxed9954c01bb89b47
wx.open.app_secret=a7482517235173ddb4083788de60b90e
wx.open.redirect_url=http://localhost:8160/api/ucenter/wx/callback
yygh.baseUrl=http://localhost:3000


```

# 一、阿里云oss

用户认证需要上传证件图片、首页轮播也需要上传图片，因此我们要做文件服务，阿里云oss是一个很好的分布式文件服务系统，所以我们只需要集成阿里云oss即可

## 1、开通“对象存储OSS”服务

```sh
# 阿里云accesskeyskey
aliyun.oss.endpoint=oss-cn-shanghai.aliyuncs.com
aliyun.oss.accessKeyId=个人的keyid
aliyun.oss.secret=个人密钥
aliyun.oss.bucket=yygh-xuda

```
![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205314_7ca170cc_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205323_12c5ed9d_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205333_011387aa_8952851.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205349_827eb87b_8952851.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205751_67b3561f_8952851.png "屏幕截图.png")


![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205805_ff362460_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205833_749d8969_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205847_f1d65a03_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205901_69eaabca_8952851.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205916_7ba2b0b9_8952851.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/205946_31e75942_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/210000_c5b107a4_8952851.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/210008_ca014b00_8952851.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2022/0417/210019_4f6a2fe0_8952851.png "屏幕截图.png")