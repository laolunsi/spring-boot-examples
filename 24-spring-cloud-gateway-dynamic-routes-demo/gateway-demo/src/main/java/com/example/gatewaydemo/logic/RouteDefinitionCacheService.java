package com.example.gatewaydemo.logic;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

public interface RouteDefinitionCacheService {

    List<RouteDefinition> getRouteDefinitions();

    boolean saveAll(List<RouteDefinition> definitions);

    boolean has(String routeId);

    boolean delete(String routeId);

    boolean save(RouteDefinition routeDefinition);

}
