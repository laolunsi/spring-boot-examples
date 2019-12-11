package com.example.demo.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.bean.User;

@Mapper
public interface UserDAO {
	
	public User find(@Param("name")String name, @Param("password")String password);

	// 注： CRTL+Shift+O，快捷导入所有import
}
