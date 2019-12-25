package com.example.paramstime;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-12-02 22:08
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @GetMapping(value = "")
    public String index(Date time) {
        System.out.println("index time：" + time);
        return "this is your param: " + time;
    }

    @PostMapping(value = "")
    public String indexByPost(Date time) {
        System.out.println("index time：" + time);
        return "this is your param by post: " + time;
    }

    @PostMapping(value = "user")
    public String saveUser(User user) {
        System.out.println("user to save: " + user);
        return "this is your user to save: " + user;
    }

    @PostMapping(value = "user/body")
    public String saveUserByBody(@RequestBody User user) {
        System.out.println("user to save: " + user);
        return "this is your user to save by body: " + user;
    }

    @PostMapping(value = "user_list")
    public String saveUserList(@RequestBody UserSaveForm saveForm) {
        if (saveForm != null && saveForm.getUserList() != null) {
            saveForm.getUserList().forEach(a -> {
                System.out.println("user in list to save: " + a);
            });
        }

        return saveForm != null && saveForm.getUserList() != null
                ? "your user list size: " + saveForm.getUserList().size() : "your user list is null";
    }
}
