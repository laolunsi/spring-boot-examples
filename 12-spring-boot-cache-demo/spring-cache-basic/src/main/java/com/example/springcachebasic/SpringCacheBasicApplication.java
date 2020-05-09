package com.example.springcachebasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringCacheBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheBasicApplication.class, args);
    }

}
