# Spring Boot Examples 升级基线说明

> 目标：统一仓库升级方向，优先保证 **Java LTS + Spring Boot/Spring Cloud 官方支持组合**，并通过 POC 分批落地。

## 1. 目标技术基线

| 维度 | 基线 A（优先） | 基线 B（过渡/兼容） | 说明 |
| --- | --- | --- | --- |
| Java | **21 (LTS)** | **17 (LTS)** | 新增示例优先 Java 21，存量迁移允许先到 17 |
| Spring Boot | **3.3.x** | **3.2.x** | 避免继续使用 Boot 2.x 新增实现 |
| Spring Cloud | **2023.0.x** | **2022.0.x** | 与 Boot 3.x 组合使用 |
| Spring Cloud Alibaba（如使用） | **2023.x 对应版本** | - | 统一跟随 Boot 3.x 生态 |

### 基线原则

1. 不再新增 Java 8 + Boot 2.x 的示例。
2. 含安全能力（OAuth2/OIDC）的示例优先采用 Spring Security 官方推荐新栈。
3. 网关/配置中心等云原生项目优先验证 Boot 3 + Cloud 2023 兼容性。

---

## 2. 分批升级策略（按主题）

### 第一批（POC）

1. `13-sso-oauth2-demo`（安全主题）
2. `24-spring-cloud-gateway-dynamic-routes-demo`（网关主题）

POC 目标：

- 形成可复制的依赖升级清单；
- 沉淀典型代码迁移模式（配置类、过滤器、安全链、配置文件）；
- 输出“旧实现 -> 新实现”的对照说明，减少读者误用过时方案。

### 第二批（建议）

- `23-spring-cloud-gateway-nacos-routes`
- `22-spring-cloud-nacos-config-demo`
- `19-spring-cloud-ali-nacos-demo`

### 第三批（建议）

- 其余 Spring Boot 单体示例（缓存、Redis、日志、异常处理等），统一到 Java 17+/Boot 3.x。

---

## 3. POC 迁移记录

## 3.1 `13-sso-oauth2-demo`（OAuth2）

### 当前状态（待迁移）

- 使用 `spring-security-oauth2-autoconfigure` 与 `@EnableAuthorizationServer`；
- 使用 `WebSecurityConfigurerAdapter`、`@EnableOAuth2Sso` 旧写法；
- 依赖与配置体系属于 Spring Security OAuth（已停止演进）。

### 依赖替换（目标）

| 旧依赖/能力 | 新依赖/能力 |
| --- | --- |
| `spring-security-oauth2-autoconfigure` | `org.springframework.security:spring-security-oauth2-authorization-server` |
| `@EnableAuthorizationServer` | `SecurityFilterChain` + Authorization Server DSL |
| `@EnableOAuth2Sso` | `spring-boot-starter-oauth2-client` + `oauth2Login()` |
| 旧资源服务配置 | `spring-boot-starter-oauth2-resource-server` + JWT/opaque token 校验 |

### 代码迁移点（POC 清单）

1. 用 `SecurityFilterChain` 替换 `WebSecurityConfigurerAdapter`。
2. 认证服务端拆分两条安全链：
   - Authorization Server endpoints 安全链；
   - 业务/登录 endpoints 安全链。
3. 客户端（client-a/client-b）改为标准 OAuth2 Client 配置：
   - `spring.security.oauth2.client.registration.*`
   - `spring.security.oauth2.client.provider.*`
4. 若需要保护业务 API，引入 Resource Server，并明确 token 策略（JWT 推荐）。

---

## 3.2 `24-spring-cloud-gateway-dynamic-routes-demo`（Gateway）

### 当前状态（待迁移）

- Java 8、较老版本 MySQL 驱动与 MyBatis Starter；
- 代码中存在旧版网关动态路由实现方式，缺乏 Boot 3 基线说明。

### 依赖替换（目标）

| 旧依赖/能力 | 新依赖/能力 |
| --- | --- |
| Java 8 | Java 17/21 |
| `mysql:mysql-connector-java:5.1.38` | `com.mysql:mysql-connector-j`（8.x） |
| `mybatis-spring-boot-starter:2.0.0` | `mybatis-spring-boot3-starter`（Boot 3 对应） |
| 老版 Spring Cloud Gateway 组合 | Boot 3.3 + Cloud 2023 组合 |

### 代码迁移点（POC 清单）

1. `javax.*` 命名空间迁移为 `jakarta.*`（如涉及校验/Servlet API）。
2. 网关过滤器与路由刷新逻辑核对 Reactor API 兼容性。
3. 配置文件从旧属性名迁移到 Boot 3 推荐写法。
4. 校验数据库驱动类名、时区参数、连接池参数兼容性。

---

## 4. 验收与文档要求

每个 POC 子项目至少输出以下内容：

1. 可运行版本（README 含启动步骤）；
2. 依赖替换对照表（旧 -> 新）；
3. 关键代码迁移点说明（含安全配置或路由配置）；
4. 常见报错与排查（如 `NoSuchMethodError`、`jakarta` 包缺失、401/redirect 问题）。
