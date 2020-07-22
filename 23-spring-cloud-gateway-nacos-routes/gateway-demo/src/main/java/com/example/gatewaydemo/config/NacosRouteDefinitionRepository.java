package com.example.gatewaydemo.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.alibaba.nacos.NacosConfigProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * nacos 路由数据源
 */
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final Logger log = LoggerFactory.getLogger(NacosRouteDefinitionRepository.class);

    private ApplicationEventPublisher publisher;

    private NacosConfigProperties nacosConfigProperties;

    public NacosRouteDefinitionRepository(ApplicationEventPublisher publisher, NacosConfigProperties nacosConfigProperties) {
        this.publisher = publisher;
        this.nacosConfigProperties = nacosConfigProperties;
        System.out.println(GatewayConfig.NACOS_DATA_ID + ", " + GatewayConfig.NACOS_GROUP_ID);
        addListener();
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            String content = nacosConfigProperties.configServiceInstance()
                    .getConfig(GatewayConfig.NACOS_DATA_ID, GatewayConfig.NACOS_GROUP_ID,5000);
            List<RouteDefinition> routeDefinitions = getListByStr(content);
            return Flux.fromIterable(routeDefinitions);
        } catch (NacosException e) {
            log.error("getRouteDefinitions by nacos error", e);
        }
        return Flux.fromIterable(CollUtil.newArrayList());
    }

    /**
     * 添加Nacos监听
     */
    private void addListener() {
        try {
            nacosConfigProperties.configServiceInstance().addListener(GatewayConfig.NACOS_DATA_ID, GatewayConfig.NACOS_GROUP_ID, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("自动更新配置...\r\n" + configInfo);
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        } catch (NacosException e) {
            log.error("nacos-addListener-error", e);
        }
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    private List<RouteDefinition> getListByStr(String content) {
        if (StrUtil.isNotEmpty(content)) {
            return JSONObject.parseArray(content, RouteDefinition.class);
        }
        return new ArrayList<>(0);
    }
}
