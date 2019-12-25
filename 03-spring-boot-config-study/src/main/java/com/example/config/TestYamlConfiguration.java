package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 11:09
 */
@Configuration
@PropertySource(value = "classpath:test.yml", encoding = "utf-8", factory = YamlSourceFactory.class)
@ConfigurationProperties(prefix = "yml.hello")
public class TestYamlConfiguration {
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
