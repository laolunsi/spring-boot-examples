# SpringCloud断路器之Hystrix

> 1. 服务治理：基于consul
> 2. 服务提供者service-producer
> 3. 服务消费者service-consumer-1: 使用Feign+Hystrix
> 4. 服务消费者service-consumer-2: 使用ribbon+Hystrix


注：本示例采用SpringBoot2.1.7.RELEASE和SpringCloud Greenwich.SR2。
在使用SpringBoot2.0.x和SpringCloud Finchley.RELEASE版本时，发现Feign的Hystrix
开启配置是没有提示的（也会生效，但是没有代码提示，很奇怪）
```yaml
# 加入这个配置，用于启动feign自带的断路器
feign:
  hystrix:
    enabled: true
```



