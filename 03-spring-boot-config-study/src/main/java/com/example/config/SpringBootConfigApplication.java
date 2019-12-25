package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
/*@EnableConfigurationProperties({MyConfiguration.class, TestConfiguration.class
, TestYamlConfiguration.class
})*/
// 不建议使用@EnableConfigurationProperties注解来启用@ConfigurationProperties
public class SpringBootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigApplication.class, args);
    }

}
