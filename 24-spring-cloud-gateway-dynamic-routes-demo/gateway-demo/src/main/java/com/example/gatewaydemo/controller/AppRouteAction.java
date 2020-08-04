package com.example.gatewaydemo.controller;

import com.example.gatewaydemo.logic.AppRouteService;
import com.example.gatewaydemo.model.beans.AppRoute;
import com.example.gatewaydemo.model.beans.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/7/23 10:05
 */
@RestController
@RequestMapping(value = "app/route")
public class AppRouteAction {
    @Autowired
    private AppRouteService appRouteService;

    @GetMapping(value = "list")
    public JsonResult list() {
        JsonResult jsonResult = new JsonResult(true);
        jsonResult.put("routeList", appRouteService.findAll());
        return jsonResult;
    }

    @PostMapping(value = "")
    public JsonResult save(AppRoute route) {
        if (route == null || StringUtils.isBlank(route.getRouteId())) {
            return new JsonResult(false, "id不能为空");
        } else if (StringUtils.isBlank(route.getUri())) {
            return new JsonResult(false, "uri不能为空");
        }

        AppRoute oldRoute = null;
        if (route.getId() != null) {
            oldRoute = appRouteService.findById(route.getId());
            if (oldRoute == null || oldRoute.getId() == null) {
                return new JsonResult(false, "数据不存在或已被删除");
            }
        }

        AppRoute sameRouteIdObj = appRouteService.findByRouteId(route.getRouteId());
        if (sameRouteIdObj != null && sameRouteIdObj.getId() != null) {
            if (route.getId() == null) {
                return new JsonResult(false, "已存在相同 RouteId 的配置");
            }
        }
        route.setPredicates(route.getPredicates() != null ? route.getPredicates().trim() : null);
        route.setFilters(route.getFilters() != null ? route.getFilters().trim() : null);

        boolean res = appRouteService.saveOrUpdate(route);
        return new JsonResult(res, res ? "操作成功" : "操作失败");
    }

    @DeleteMapping(value = "{routeId}")
    public JsonResult delete(@PathVariable("routeId") String routeId) {
        AppRoute route = appRouteService.findByRouteId(routeId);
        if (route == null || StringUtils.isBlank(route.getRouteId())) {
            return new JsonResult(false, "路由不存在");
        }

        boolean res = appRouteService.delete(route);
        return new JsonResult(res, res ? "操作成功" : "操作失败");
    }
}
