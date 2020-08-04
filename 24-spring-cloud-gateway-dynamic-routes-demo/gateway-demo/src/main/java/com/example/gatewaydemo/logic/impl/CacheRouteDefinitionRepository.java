package com.example.gatewaydemo.logic.impl;

import com.example.gatewaydemo.logic.RouteDefinitionCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 基于 Redis 实现的路由信息管理
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/7/23 14:53
 */
@Service
public class CacheRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private RouteDefinitionCacheService cacheService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> list = cacheService.getRouteDefinitions();
        return Flux.fromIterable(list);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            cacheService.save(r);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
           if (cacheService.has(id)) {
               cacheService.delete(id);
               return Mono.empty();
           }

           return Mono.defer(() -> Mono.error(new NotFoundException("未找到路由配置：" + routeId)));
        });
    }
}
