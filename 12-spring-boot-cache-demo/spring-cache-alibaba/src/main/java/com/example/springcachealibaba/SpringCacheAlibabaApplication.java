package com.example.springcachealibaba;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMethodCache(basePackages = "com.example.springcachealibaba")
@EnableCreateCacheAnnotation
@SpringBootApplication
public class SpringCacheAlibabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheAlibabaApplication.class, args);
    }

}
