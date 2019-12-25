package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 10:08
 */
@RestController
@RequestMapping(value = "")
public class IndexAction {

    @Value("${eknown.email}")
    private String email;

    @Value("${eknown.uri}")
    private String uri;
    // get by bean
    @Autowired
    private MyBean bean;
    // get by config
    @Autowired
    private MyConfiguration configuration;

    @GetMapping(value = "")
    public String getConcat() {
        return email + "<br />" + uri;
    }

    @GetMapping(value = "bean")
    public String getConcatByBean() {
        return "from bean: <br />" + bean.getEmail() + "<br />" + bean.getUri();
    }

    @GetMapping(value = "config")
    public String getConcatByConfig() {
        return "from config: <br />" + bean.getEmail() + "<br />" + bean.getUri()
                + "<br />" + configuration.getTitle();
    }
}
