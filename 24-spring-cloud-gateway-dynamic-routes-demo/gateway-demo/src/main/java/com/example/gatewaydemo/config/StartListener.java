package com.example.gatewaydemo.config;

import com.example.gatewaydemo.logic.AppRouteService;
import com.example.gatewaydemo.logic.RouteDefinitionCacheService;
import com.example.gatewaydemo.model.beans.AppRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/4 10:58
 */
@Component
public class StartListener {

    private static final Logger logger = LoggerFactory.getLogger(StartListener.class);

    @Autowired
    private RouteDefinitionCacheService cacheService;

    @Autowired
    private AppRouteService routeService;

    @PostConstruct
    public void init() {
        logger.info("初始化路由数据...");
        List<AppRoute> routeList = routeService.findAll();
        if (routeList != null && routeList.size() > 0) {
            cacheService.saveAll(routeList.stream().map(AppRoute::parseToRoute).collect(Collectors.toList()));
        }
    }
}
