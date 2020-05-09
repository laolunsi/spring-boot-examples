package com.example.springcachealibaba;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/24 11:14
 */
@RestController
@RequestMapping(value = "user")
public class UserAction {

    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    public String save(User user) {
        System.out.println("user to save: " + user);
        return "" + userService.save(user);
    }

    @DeleteMapping(value = "{userId}")
    public String delete(@PathVariable Integer userId) {
        return "" + userService.deleteById(userId);
    }

    @GetMapping(value = "{userId}")
    public String find(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return user.toString();
        }

        return "获取失败";
    }

    @GetMapping(value = "list")
    public List<User> list() {
        return userService.findAll();
    }

}
