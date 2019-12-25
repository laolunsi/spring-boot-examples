package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 10:25
 */
@Component
public class MyBean {

    @Value("${eknown.email}")
    private String email;

    @Value("${eknown.uri}")
    private String uri;

    public String getEmail() {
        return email;
    }

    public String getUri() {
        return uri;
    }
}
