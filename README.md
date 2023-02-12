# records-spring

## 项目说明

> 现阶段项目分为： records-spring和records-ng前后台两部分，并且目前处于开发状态。项目总体分为：首页、店铺、商品、订单、
> 应用、客户、存储、采购、资产、数据和系统模块。

## 前言

`records`项目致力于打造一个完整的电商综合服务平台，采用现阶段流行技术实现。

## 项目介绍

`records`项目是一套电商综合服务平台，包括前台商铺系统及后台管理系统，基于SpringBoot+MyBatis实现，采用Docker容器化部署。

### 组织结构

``` lua
mall
├── records-common -- 工具类及通用代码
├── records-security -- SpringSecurity封装公用模块
├── records-admin -- 后台管理系统接口
└── records-demo -- 框架搭建时的测试代码
```

### 技术选型

#### 后端技术

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架      | https://spring.io/projects/spring-boot         |
| SpringSecurity       | 认证和授权框架      | https://spring.io/projects/spring-security     |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html |
| Elasticsearch        | 搜索引擎            | https://github.com/elastic/elasticsearch       |
| RabbitMQ             | 消息队列            | https://www.rabbitmq.com/                      |
| Redis                | 内存数据存储         | https://redis.io/                              |
| MongoDB              | NoSql数据库         | https://www.mongodb.com                        |
| Docker               | 应用容器引擎        | https://www.docker.com                         |
| OSS                  | 对象存储            | https://github.com/aliyun/aliyun-oss-java-sdk  |
| MinIO                | 对象存储            | https://github.com/minio/minio                 |
| JWT                  | JWT登录支持         | https://github.com/jwtk/jjwt                   |
| Lombok               | Java语言增强库      | https://github.com/rzwitserloot/lombok         |
| PageHelper           | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                 |

#### 前端技术

| 技术         | 说明                  | 官网                                   |
|------------| --------------------- | -------------------------------------- |
| Angular    | 前端框架              | https://angular.io/                   |
| Ant Design | 前端UI框架            | https://ng.ant.design/               |

## 许可证

[Apache License 2.0](https://github.com/chanfaya/records-spring/blob/master/LICENSE)

Copyright (c) 2023 chanfa
