# SpringCloud Gateway + Nacos 实现动态路由示例

## gateway-demo

GatewayDemo 项目演示了 SpringCloud + Gateway 接入 Nacos，并使用动态路由配置方式。

子项目下的 config.json 是示例的动态路由配置。
以 demo 项目为例，演示了动态路由、限流等的基本配置。


## demo

DEMO 项目是一个简单的服务生产者，提供了 `/test/hello` 接口，用于测试使用。

---

参考：

1. [SpringCloud Gateway + Nacos 动态路由（含示例源码）](https://www.cnblogs.com/zlt2000/p/11712943.html)
2. [服务网关 Spring Cloud GateWay 熔断、限流、重试 -- 纯洁的微笑](http://www.ityouknow.com/springcloud/2019/01/26/spring-cloud-gateway-limit.html)
3. [spring cloud gateway 之限流篇 -- 方志朋](https://juejin.im/post/5c18fba151882509a76856d3)
