package com.example.shardspheredemo.dao;

import com.example.shardspheredemo.bean.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ConfigDAO {

    boolean insert(Config config);
}
