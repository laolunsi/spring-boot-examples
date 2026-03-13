# 13-sso-oauth2-demo（迁移中）

> ⚠️ 本示例当前代码仍包含旧版 `spring-security-oauth2-autoconfigure` 技术栈，仅用于历史示例参考。新的 OAuth2/OIDC 实践请优先采用 Spring Security 官方推荐新栈（Authorization Server / Resource Server / OAuth2 Client）。

## 为什么要迁移

当前项目中使用了以下已过时方案：

- `@EnableAuthorizationServer`
- `@EnableOAuth2Sso`
- `WebSecurityConfigurerAdapter`
- `spring-security-oauth2-autoconfigure`

这些方案在 Spring Boot 3 / Spring Security 6 体系下不再是推荐路径。

## 迁移目标

- Java 升级到 17/21；
- Spring Boot 升级到 3.2+/3.3+；
- 用 **Spring Authorization Server** 替换原授权服务实现；
- client-a/client-b 使用标准 `oauth2Login()` 客户端接入；
- 如需保护 API，增加 Resource Server 并明确 token 校验策略。

## 分步迁移建议（POC）

### Step 1：替换依赖

- 移除：`org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure`
- 新增：
  - `org.springframework.security:spring-security-oauth2-authorization-server`
  - `org.springframework.boot:spring-boot-starter-oauth2-client`
  - `org.springframework.boot:spring-boot-starter-oauth2-resource-server`（按需）

### Step 2：重写安全配置

- 用 `SecurityFilterChain` 替换 `WebSecurityConfigurerAdapter`；
- 在 auth-server 里拆分两条安全链：
  1. 授权服务器端点链（authorize/token/jwks 等）；
  2. 应用登录与业务端点链。

### Step 3：迁移客户端配置

将 client-a/client-b 的配置迁移到：

- `spring.security.oauth2.client.registration.<id>.*`
- `spring.security.oauth2.client.provider.<id>.*`

并使用 `oauth2Login()` 完成登录流程。

### Step 4：补充文档与兼容提示

- 给出旧配置与新配置对照表；
- 明确 Authorization Code + PKCE（可选）示例；
- 记录常见 401/重定向 URI 不匹配问题排查方式。

## 读者提示

如果你是首次学习 OAuth2 登录与单点登录，请直接按仓库根目录 `docs/version-baseline.md` 的目标基线进行实践，避免继续复制旧实现。
