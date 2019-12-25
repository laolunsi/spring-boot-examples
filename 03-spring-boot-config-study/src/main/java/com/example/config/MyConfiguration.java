package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 10:30
 */
@Component
@ConfigurationProperties(prefix = "eknown")
public class MyConfiguration {
    private String email;
    private String uri;
    private String title;

    // 必须有setter，否则无法从配置文件中读取然后赋值

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
