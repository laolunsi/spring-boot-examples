package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 10:48
 */
@Configuration
@PropertySource("classpath:test.properties")
//@PropertySource("classpath:test.yml") // 注意，yml文件不能直接这样写，会读不出数据
@ConfigurationProperties(prefix = "hello")
public class TestConfiguration {
    private String name;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
