package com.example.gatewaydemo.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/7/21 14:19
 */
@Configuration
public class LimitFilter {

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
