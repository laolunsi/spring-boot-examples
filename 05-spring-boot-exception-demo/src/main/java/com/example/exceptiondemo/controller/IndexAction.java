package com.example.exceptiondemo.controller;

import com.example.exceptiondemo.exception.UserNotExistException;
import com.example.exceptiondemo.model.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/12/5 15:33
 */
@RestController
@RequestMapping(value = "")
public class IndexAction {

    @GetMapping(value = "add")
    public Integer add(Integer a, Integer b) {
        return a+b;
    }

    @GetMapping(value = "user/{id}")
    public JsonResult findUser(@PathVariable Integer id) throws UserNotExistException {
        if (id < 100) {
            throw new UserNotExistException(id);
        }

        return new JsonResult(true, "暂未实现用户查询功能");
    }
}
