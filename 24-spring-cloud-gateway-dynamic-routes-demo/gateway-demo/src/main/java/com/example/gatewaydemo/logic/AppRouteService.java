package com.example.gatewaydemo.logic;

import com.example.gatewaydemo.model.beans.AppRoute;

import java.util.List;

public interface AppRouteService {

    List<AppRoute> findAll();

    boolean saveOrUpdate(AppRoute route);

    boolean delete(AppRoute route);

    AppRoute findByRouteId(String routeId);

    AppRoute findById(Integer id);
}
