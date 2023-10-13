# Easy Health Care

# Hospital Interface Simulation System

Version：V1.0

1. Document Introduction

1.1 Document Explanation
This system is designed for simulating the hospital-side interface, cooperating with ShangYiTong for interface debugging.

1.2 Intended Audience
Developers and testers.

2. Service Deployment

Locate the hospital-manage project in the resource folder and import it into IDEA.
Import SQL. Path: hospital-manage/resource folder/sql/table structure.sql.
Modify the application-dev.yml file for database connection.
Start the project.
3. Operation Instructions

3.1 Accessing the Project
Browser: http://localhost:9999/, as shown in the figure.

3.2 Hospital Configuration
Configure the hospital information set in the ShangYiTong management background to the "Hospital Interface Simulation System", as illustrated.

Configuration parameters:

Hospital code: Hospital code allocated by ShangYiTong.
Signature key: Signature key allocated by ShangYiTong.
Unified appointment and registration platform base path: ShangYiTong interface base path.
3.3 Uploading the Interface
Refer to "ShangYiTong API Interface Document.docx".
Hospitals, departments, and shifts, etc.
Test data is located at: hospital-manage/resource folder/sample data.

3.4 Callback Interface
Refer to "ShangYiTong API Interface Document.docx".
Booking orders, updating payment status, and canceling appointments, etc.


Easy Health Care

Version: V1.0
1. Project Summary
1.1 Project Function Summary (Backend Management System)

1 Hospital Setup Management

(1) Hospital settings list, add, lock, delete
(2) Hospital list, details, scheduling, offline
2 Data Management

(1) Data dictionary tree display, import, export
3 User Management

(1) User list, view, lock
(2) Authenticated user approval
4 Order Management

(1) Order list, details
5 Statistics Management

(1) Reservation statistics
1.2 Project Function Summary (Front-end User System)

1 Home Page Data Display

(1) Hospital list
2 Hospital Detail Display

(1) Hospital department display
3 User Login Function

(1) Mobile login (send SMS verification code)
(2) WeChat scan login
4 User Real-name Authentication

5 Patient Management

(1) List, add, details, delete
6 Appointment Registration Function

(1) Scheduling and registration detail information
(2) Confirm registration information
(3) Generate appointment registration order
(4) Registration order payment (WeChat)
(5) Cancel appointment order
7 Medical Reminder Function

1.3 Project Technical Summary (Backend Technology)

1 SpringBoot

2 SpringCloud

(1) Nacos registration center
(2) Feign
(3) GateWay
3 Redis

(1) Use Redis as cache
(2) Verification code validity period, payment QR code validity period
4 MongoDB

(1) Use MongoDB to store hospital-related data
5 EasyExcel

(1) Operate excel table, read and write operations
6 MyBatisPlus

7 RabbitMQ

(1) Order-related operations, send mq messages
8 Docker

(1) Download image docker pull
(2) Create container docker run
9 Aliyun OSS

10 Aliyun SMS Service

11 WeChat Login/Payment

12 Scheduled Task

1.4 Project Technical Summary (Front-end Technology)

1 Vue

(1) Directives
2 Element-ui

3 Nuxt

4 Npm

5 ECharts

# Module Introduction

hospital-manage: Hospital interface simulation end (developed, used directly)

yygh-parent: Root directory, managing sub-modules:

common: Common module parent node

common-util: Utility module, all modules can depend on it

rabbit-util: rabbitmq business encapsulation

service-util: Service utility package, containing common configuration classes for service, all service modules depend on it

server-gateway: Service gateway

model: Entity module

service: API service parent node

service-hosp: Hospital API service

service-cmn: Common API service

service-user: User API service

service-order: Order API service

service-oss: File API service

service-sms: SMS API service

service-task: Scheduled task service

service-statistics: Statistics API service

service-client: feign service invocation parent node

service-cmn-client: Common API interface

service-hosp-client: Hospital API interface

service-order-client: Order API interface

service-user: User API interface

![1650179424674](C:\Users\86176\AppData\Roaming\Typora\typora-user-images\1650179424674.png)

# Information to Start

 ### My Redis, MySQL, and Nacos are on my local Windows, while my MongoDB and RabbitMQ are on my server.

```shell
// Information to start
# Redis configuration
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.database= 0
spring.redis.timeout=1800000
spring.redis.lettuce.pool.max-active=20
spring.redis.lettuce.pool.max-wait=-1
# Max block wait time (negative indicates no limit)
spring.redis.lettuce.pool.max-idle=5
spring.redis.lettuce.pool.min-idle=0

# MySQL database connection
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/yygh_cmn?characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=

# Nacos service address
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848

# MongoDB connection settings
spring.data.mongodb.uri=mongodb://106.14.60.212:27017/yygh_hosp
# Global time format for returned JSON
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8

# RabbitMQ address
spring.rabbitmq.host=106.14.60.212
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# WeChat return address from Silicon Valley, the port to open the WeChat QR code is the local return 8160
wx.open.app_id=wxed9954c01bb89b47
wx.open.app_secret=a7482517235173ddb4083788de60b90e
wx.open.redirect_url=http://localhost:8160/api/ucenter/wx/callback
yygh.baseUrl=http://localhost:3000


```

# I. Aliyun OSS

User verification requires uploading ID photos, and the homepage carousel also requires uploading photos. Therefore, we need to provide a file service. Aliyun OSS is a good distributed file service system, so we only need to integrate Aliyun OSS.

## 1. Activate the "Object Storage Service (OSS)"


```sh
# Aliyun access keys
aliyun.oss.endpoint=oss-cn-shanghai.aliyuncs.com
aliyun.oss.accessKeyId=personal keyid
aliyun.oss.secret=personal secret key
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
