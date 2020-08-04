package com.example.gatewaydemo.logic.impl;

import com.example.gatewaydemo.config.RouteHandler;
import com.example.gatewaydemo.logic.AppRouteService;
import com.example.gatewaydemo.logic.RouteDefinitionCacheService;
import com.example.gatewaydemo.model.beans.AppRoute;
import com.example.gatewaydemo.model.dao.AppRouteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppRouteServiceImpl implements AppRouteService {

    private static final Logger logger = LoggerFactory.getLogger(AppRouteService.class);

    @Autowired
    private AppRouteDAO appRouteDAO;

    @Autowired
    private RouteDefinitionCacheService cacheService;

    @Autowired
    private RouteHandler routeHandler;

    @Override
    public List<AppRoute> findAll() {
        return appRouteDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(AppRoute route) {
        route.setUpdateTime(new Date());
        AppRoute oldRoute = appRouteDAO.findById(route.getId());
        boolean res = false;
        if (oldRoute != null && oldRoute.getId() != null) {
            res = appRouteDAO.update(route);
        } else {
            res = appRouteDAO.insert(route);
        }

        if (res) {
            logger.info("更新缓存，通知网关重新加载路由信息...");
            cacheService.save(route.parseToRoute());
            routeHandler.loadRouteConfig();
        }

        return res;
    }

    @Override
    public boolean delete(AppRoute route) {
        route.setUpdateTime(new Date());
        boolean res = appRouteDAO.delete(route);
        if (res) {
            logger.info("更新缓存，通知网关重新加载路由信息...");
            cacheService.save(route.parseToRoute());
            routeHandler.loadRouteConfig();
        }
        return res;
    }

    @Override
    public AppRoute findByRouteId(String routeId) {
        return appRouteDAO.findByRouteId(routeId);
    }

    @Override
    public AppRoute findById(Integer id) {
        return appRouteDAO.findById(id);
    }
}
