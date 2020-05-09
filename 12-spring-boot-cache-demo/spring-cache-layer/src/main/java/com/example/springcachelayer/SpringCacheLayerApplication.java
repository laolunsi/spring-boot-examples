package com.example.springcachelayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringCacheLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheLayerApplication.class, args);
    }

}
