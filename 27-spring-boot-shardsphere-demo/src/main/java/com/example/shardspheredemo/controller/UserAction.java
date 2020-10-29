package com.example.shardspheredemo.controller;

import com.example.shardspheredemo.bean.User;
import com.example.shardspheredemo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/27 15:54
 */
@RestController
@RequestMapping(value = "user")
public class UserAction {

    @Autowired
    private UserDAO orderDAO;

    @PostMapping("")
    public Object save(User user) {
        if (user == null || StringUtils.isEmpty(user.getName())) {
            return "name 不能为空";
        } else if (StringUtils.isEmpty(user.getPassword())) {
            return "password 不能为空";
        }

        user.setCreateTime(new Date());

        return orderDAO.insert(user);
    }

    @GetMapping(value = "list")
    public Object list(Integer currentIndex, Integer limit) {
        if (currentIndex == null || currentIndex < 0 || limit == null || limit <= 0) {
            currentIndex = 0;
            limit = 10;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count", orderDAO.count());
        map.put("list", orderDAO.find(currentIndex, limit));
        return map;
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Integer id) {
        return "id: " + id;
    }
}
