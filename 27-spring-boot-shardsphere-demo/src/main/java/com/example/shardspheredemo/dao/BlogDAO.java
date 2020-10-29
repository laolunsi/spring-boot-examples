package com.example.shardspheredemo.dao;

import com.example.shardspheredemo.bean.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogDAO {

    boolean insert(Blog blog);

    boolean update(Blog blog);

    List<Blog> find(@Param("currentIndex") Integer currentIndex, @Param("limit") Integer limit);

    int count();

    Blog findById(@Param("id") Long id);

    List<Blog> findByUserId(@Param("userId") Long userId);

    boolean deleteById(@Param("id") Long id);

}
