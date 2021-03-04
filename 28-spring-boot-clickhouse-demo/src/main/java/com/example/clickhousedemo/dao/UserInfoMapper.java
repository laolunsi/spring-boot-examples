package com.example.clickhousedemo.dao;

import com.example.clickhousedemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    /**
     * 存储新的数据
     * @param userInfo
     */
    void saveData (UserInfo userInfo) ;

    /**
     * 根据 id 查询用户
     * @param id
     * @return
     */
    UserInfo selectById (@Param("id") Integer id) ;

    /**
     * 查询全部数据
     * @return
     */
    List<UserInfo> selectList () ;
}

