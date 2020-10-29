package com.example.shardspheredemo.controller;

import com.example.shardspheredemo.bean.Config;
import com.example.shardspheredemo.dao.ConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/27 19:49
 */
@RestController
@RequestMapping("config")
public class ConfigAction {

    @Autowired
    private ConfigDAO configDAO;

    @PostMapping("")
    public Object save(Config config) {
        if (config == null || StringUtils.isEmpty(config.getRemark())) {
            return "remark 不能为空";
        }

        config.setCreateTime(new Date());
        config.setLastModifyTime(new Date());
        return configDAO.insert(config);
    }
}
