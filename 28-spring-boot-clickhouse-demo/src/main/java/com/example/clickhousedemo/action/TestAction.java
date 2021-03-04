package com.example.clickhousedemo.action;

import com.example.clickhousedemo.dao.UserInfoMapper;
import com.example.clickhousedemo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @since 2021/3/3
 */
@RestController
@RequestMapping("user")
public class TestAction {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("list")
    public Object findAll() {
        List<UserInfo> list = userInfoMapper.selectList();
        return list;
    }

    @GetMapping("{id}")
    public Object findById(@PathVariable Integer id) {
        return userInfoMapper.selectById(id);
    }

    @PostMapping("")
    public Object save(UserInfo userInfo) {
        if (userInfo == null || userInfo.getId() == null || userInfo.getName() == null) {
            return "参数错误";
        }
        userInfo.setCreateTime(new Date());
        userInfoMapper.saveData(userInfo);
        return userInfoMapper.selectById(userInfo.getId());
    }
}
