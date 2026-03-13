# SpringCloud Gateway 动态路由

## gateway-demo

GatewayDemo 项目演示了 SpringCloud + Gateway 利用 Nacos 作为注册中心，Mysql 做路由配置来实现动态路由管理功能。

提供了 list/save/delete 接口用于对路由信息进行管理。

子项目下的 config.json 是示例的动态路由配置。将拆分其数据存储到 Mysql 的 app_route 表中。

## demo

DEMO 项目是一个简单的服务生产者，提供了 `/test/hello` 接口，用于测试使用。

---

## 升级 POC 记录（Boot 3 / Cloud 2023）

### 依赖替换建议

- Java：`1.8` -> `17/21`
- MySQL 驱动：`mysql:mysql-connector-java:5.x` -> `com.mysql:mysql-connector-j:8.x`
- MyBatis：`mybatis-spring-boot-starter:2.x` -> `mybatis-spring-boot3-starter`
- Spring Cloud Gateway：升级到与 Spring Boot 3.2+/3.3+ 对应的 Cloud 版本线。

### 代码迁移点

1. 若出现 `javax.*` 依赖，统一迁移到 `jakarta.*`。
2. 校验动态路由刷新链路在 Reactor 新版本下行为是否一致。
3. 重新核对 Redis、数据源与网关配置项命名，避免旧属性失效。
4. 增加最小化回归用例：
   - 路由增删改查；
   - 网关转发正确性；
   - 动态刷新后的生效时序。
