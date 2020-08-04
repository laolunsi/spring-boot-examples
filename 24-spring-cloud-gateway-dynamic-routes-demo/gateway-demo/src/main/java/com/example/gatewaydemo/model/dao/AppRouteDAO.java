package com.example.gatewaydemo.model.dao;

import com.example.gatewaydemo.model.beans.AppRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AppRouteDAO {

    @Select("select * from app_route")
    List<AppRoute> findAll();

    @Select("select * from app_route where routeId = #{routeId} AND `delete` = 0 LIMIT 1")
    AppRoute findByRouteId(String routeId);

    @Select("select * from app_route where id = #{id} AND `delete` = 0")
    AppRoute findById(Integer id);

    boolean update(AppRoute route);

    boolean insert(AppRoute route);

    boolean delete(AppRoute route);

}
