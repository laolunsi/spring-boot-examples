# SpringCloud Gateway 动态路由

## gateway-demo

GatewayDemo 项目演示了 SpringCloud + Gateway 利用 Nacos 作为注册中心，Mysql 做路由配置来实现动态路由管理功能。

提供了 list/save/delete 接口用于对路由信息进行管理。

子项目下的 config.json 是示例的动态路由配置。将拆分其数据存储到 Mysql 的 app_route 表中。


## demo

DEMO 项目是一个简单的服务生产者，提供了 `/test/hello` 接口，用于测试使用。