# 学习路径推荐顺序 + Demo 启动依赖地图

> 目标：给出一条从 **Spring Boot 基础** 到 **Spring Cloud 微服务治理** 的学习路线，并梳理每个 demo 的启动前置依赖（数据库 / 中间件 / 注册中心）与关键端口。

## 一、推荐学习路径（按阶段）

### 阶段 A：Spring Boot 基础能力（先打地基）
1. `03-spring-boot-config-study`（配置体系）
2. `09-spring-boot-params-time-demo`（参数绑定与时间处理）
3. `05-spring-boot-exception-demo`（统一异常处理）
4. `06-spring-boot-logback-demo`（日志体系）
5. `11-spring-boot-application-study`（Application 启动机制）
6. `04-spring-boot-swagger-demo`（接口文档）
7. `10-spring-boot-mail-demo`（邮件能力）

### 阶段 B：数据访问与缓存/消息
8. `01-spring-boot-ssm-demo`（MyBatis + MySQL 基础 CRUD）
9. `07-spring-boot-redis-demo`（Redis 接入）
10. `12-spring-boot-cache-demo`（缓存抽象与 JetCache）
11. `08-spring-boot-mongo-demo`（MongoDB 文档型存储）
12. `25-spring-boot-rabbitmq-demo`（消息队列）
13. `26-spring-boot-elasticsearch-demo`（搜索）
14. `27-spring-boot-shardsphere-demo`（分库分表）
15. `28-spring-boot-clickhouse-demo`（OLAP 场景）

### 阶段 C：认证与安全扩展
16. `13-sso-oauth2-demo`（OAuth2/SSO 基础认知）

### 阶段 D：Spring Cloud 治理体系（建议按依赖链学习）
17. `14-spring-cloud-eureka-demo`（服务注册发现）
18. `15-spring-cloud-feign-demo`（声明式调用）
19. `17-spring-cloud-ribbon-demo`（客户端负载均衡）
20. `18-spring-cloud-hystrix-demo`（熔断降级）
21. `16-spring-cloud-consul-demo`（替代注册中心方案）
22. `19-spring-cloud-ali-nacos-demo`（Nacos 注册中心）
23. `21-spring-cloud-config-demo`（配置中心）
24. `22-spring-cloud-nacos-config-demo`（Nacos 配置中心）
25. `20-spring-cloud-admin-demo`（服务健康监控）
26. `23-spring-cloud-gateway-nacos-routes`（Gateway + Nacos 动态路由）
27. `24-spring-cloud-gateway-dynamic-routes-demo`（Gateway + DB 动态路由管理）
28. `02-spring-boot-admin-demo`（单体下的监控实践，可与 Cloud Admin 对照学习）

---

## 二、每个 Demo 启动依赖地图

> 说明：
> - “外部依赖”指本地启动前通常需要先准备的基础设施。
> - “关键端口”优先记录 `application*.yml/properties` 中配置；未显式声明则写“默认 8080（或未配置）”。

| Demo | 主题 | 外部依赖（数据库/中间件/注册中心） | 关键端口（按仓库配置） |
|---|---|---|---|
| 01-spring-boot-ssm-demo | SSM 整合 | MySQL | 8081 |
| 02-spring-boot-admin-demo | Spring Boot Admin（单体） | 无强制（被监控应用按需） | 9334（admin-server）、9333（client） |
| 03-spring-boot-config-study | 配置详解 | 无 | 8080、8081（多 profile/多配置示例） |
| 04-spring-boot-swagger-demo | Swagger 接口文档 | 无 | 8106 |
| 05-spring-boot-exception-demo | 统一异常处理 | 无 | 8010 |
| 06-spring-boot-logback-demo | Logback 日志 | 无 | 8011、8012 |
| 07-spring-boot-redis-demo | Redis 接入 | Redis | 8867（应用）、6379（Redis 默认） |
| 08-spring-boot-mongo-demo | MongoDB 接入 | MongoDB | 8866 |
| 09-spring-boot-params-time-demo | 时间参数处理 | 无 | 8009 |
| 10-spring-boot-mail-demo | 邮件发送 | SMTP 服务（如 QQ/企业邮箱 SMTP） | 默认 8080（未显式声明） |
| 11-spring-boot-application-study | Application 机制 | 无 | 8081 |
| 12-spring-boot-cache-demo | 缓存实战（basic/alibaba） | Redis（尤其 alibaba 子模块） | 8081、8082、8083（子模块差异） |
| 13-sso-oauth2-demo | OAuth2 SSO | 无强制中间件（示例以内存/应用内配置为主） | 8300、8301、8302 |
| 14-spring-cloud-eureka-demo | Eureka 注册发现 | Eureka Server（本项目内提供） | 8200（server）、8201/8202/8203（clients） |
| 15-spring-cloud-feign-demo | Feign 调用 | Eureka（本项目链路依赖） | 8100（eureka）、8101（provider）、8102（consumer） |
| 16-spring-cloud-consul-demo | Consul 注册发现 | Consul | 8500（consul）、8505/8506（服务） |
| 17-spring-cloud-ribbon-demo | Ribbon 调用 | Consul（该示例采用） | 8500、8200、8201 |
| 18-spring-cloud-hystrix-demo | Hystrix 熔断 | Consul（该示例采用） | 8500、8505、8511 |
| 19-spring-cloud-ali-nacos-demo | Nacos 注册中心 | Nacos | 8200、8201 |
| 20-spring-cloud-admin-demo | Cloud Admin 监控 | Nacos（client 注册） | 9200（admin-server） |
| 21-spring-cloud-config-demo | Spring Cloud Config | Git 仓库（配置源）、注册中心（按子模块） | 8887（config server）、8021（client）、8500（consul） |
| 22-spring-cloud-nacos-config-demo | Nacos 配置中心 | Nacos | 默认 8080（未显式声明） |
| 23-spring-cloud-gateway-nacos-routes | Gateway + Nacos 动态路由 | Nacos、Redis（按示例配置） | 8502 |
| 24-spring-cloud-gateway-dynamic-routes-demo | Gateway + DB 动态路由 | MySQL、Redis、Nacos | 8502、8503 |
| 25-spring-boot-rabbitmq-demo | RabbitMQ | RabbitMQ | 8701（producer）、8702（consumer）、5672（MQ 默认） |
| 26-spring-boot-elasticsearch-demo | Elasticsearch 接入 | Elasticsearch | 8060 |
| 27-spring-boot-shardsphere-demo | ShardingSphere 分库分表 | MySQL（分库/分表数据源） | 8223 |
| 28-spring-boot-clickhouse-demo | ClickHouse 接入 | ClickHouse | 默认 8080（未显式声明） |

---

## 三、建议启动顺序（微服务类 Demo）

对于 Spring Cloud 相关示例，推荐统一遵循：
1. 先起基础设施：MySQL / Redis / MQ / ES / Nacos / Consul / Eureka / Git 配置仓库。
2. 再起注册中心与配置中心（如 Eureka / Nacos / Config Server）。
3. 再起 provider（服务提供方）。
4. 最后起 consumer / gateway / admin 等上游组件。

这样可以减少“服务启动成功但调用失败（注册中心为空/配置未拉取）”的问题。

## 四、如何使用这份地图

- **零基础上手**：先按“阶段 A → B”跑通单体能力，再进入“阶段 D”。
- **面试导向**：优先 `03/05/06/07/12/14/15/18/23/24`。
- **业务落地导向**：优先 `01/07/12/25/26/27/24`，可快速拼装典型“后台服务 + 网关 + 缓存 + 消息 + 检索”技术链。
