package com.example.gatewaydemo.config;

import com.alibaba.fastjson.JSONObject;
import com.example.gatewaydemo.logic.RouteDefinitionCacheService;
import com.example.gatewaydemo.model.beans.AppRoute;
import com.example.gatewaydemo.logic.AppRouteService;
import com.example.gatewaydemo.logic.impl.CacheRouteDefinitionRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/7/23 16:00
 */
@Service
public class RouteHandler implements ApplicationEventPublisherAware, CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RouteHandler.class);

    private ApplicationEventPublisher publisher;

    @Autowired
    private AppRouteService appRouteService;

    @Autowired
    private CacheRouteDefinitionRepository cacheRouteDefinitionRepository;

    @Autowired
    private RouteDefinitionCacheService routeDefinitionCacheService;

    @Override
    public void run(String... args) throws Exception {
        log.info("首次初始化路由....");
        this.loadRouteConfig();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void loadRouteConfig() {
        log.info("加载路由配置...");
        // 这里直接用 cacheRouteDefinitionRepository.getRouteDefinitions()，在通过接口更新路由信息后调用此方法时，会出现以下异常：
        // java.lang.IllegalStateException: Iterating over a toIterable() / toStream() is blocking, which is not supported in thread reactor-http-nio-3
        //	at reactor.core.publisher.BlockingIterable$SubscriberIterator.hasNext(BlockingIterable.java:160) ~[reactor-core-3.3.6.RELEASE.jar:3.3.6.RELEASE]
        //	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
        // Error has been observed at the following site(s)

        // 这个问题的详细解释我还没找到，目前大概理解是 WebFlux 中的异步数据 Flux<T> 被同步的操作调用时，会抛出 blocking 异常。
        // 解决办法 1. new 一个线程来从 Flux<T> 中获取数据，然后执行操作
        // 2. 这里不使用 cacheRouteDefinitionRepository.getRouteDefinitions() 了，而是直接用 routeDefinitionCacheService.getRouteDefinitions()


        Flux<RouteDefinition> definitionFlux = cacheRouteDefinitionRepository.getRouteDefinitions();
        new Thread(() -> {
            List<String> existRouteIds = definitionFlux.toStream().map(RouteDefinition::getId).collect(Collectors.toList());
            // 也可以用下面这种方法，就不需要 new Thread() 了：
            // List<String> existRouteIds = routeDefinitionCacheService.getRouteDefinitions().stream().map(RouteDefinition::getId).collect(Collectors.toList());

            List<AppRoute> appRouteList = appRouteService.findAll();
            if (appRouteList != null && appRouteList.size() > 0) {
                appRouteList.forEach(a -> {
                    if (BooleanUtils.isTrue(a.getDelete()) && existRouteIds.contains(a.getRouteId())) {
                        deleteRoute(a.getRouteId());
                    } else {
                        RouteDefinition routeDefinition = a.parseToRoute();
                        System.out.println("s: " + JSONObject.toJSONString(routeDefinition));
                        if (routeDefinition != null) {
                            cacheRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
                        }
                    }
                });
            }

            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        }).start();

    }

    public void deleteRoute(String routeId) {
        log.info("删除路由：" + routeId);
        cacheRouteDefinitionRepository.delete(Mono.just(routeId)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
