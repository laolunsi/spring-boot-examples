package com.example.swaggerdemo;

import io.swagger.annotations.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-08 23:23
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "user")
public class UserController {

    // 模拟数据库存储的用户
    private static Map<Integer, User> userMap;

    static {
        userMap = new ConcurrentHashMap<>();
        User user = new User(0, "admin", true, new Date());
        userMap.put(user.getId(), user);
    }

    @ApiOperation("列表查询")
    @GetMapping(value = "")
    public List<User> list() {
        return new ArrayList<>(userMap.values());
    }

    @ApiOperation(value = "获取用户详细信息", notes = "路径参数ID")
    @GetMapping(value = "{id}")
    public User detail(@PathVariable Integer id) {
        return userMap.get(id);
    }

    @ApiOperation(value = "新增或更新用户信息", notes = "insert和update共用"
            , response = User.class)
    @PostMapping(value = "")
    public User add(@RequestBody User user) {
        if (user == null || user.getId() == null || !StringUtils.isEmpty(user.getName())
                || userMap.containsKey(user.getId())) {
            return null;
        }

        user.setUpdateTime(new Date());
        userMap.put(user.getId(), user);
        return user;
    }


    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "{id}")
    public Boolean delete(@ApiParam(name = "用户ID", required = true, example = "100") @PathVariable Integer id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id);
            return true;
        }

        return false;
    }

}
