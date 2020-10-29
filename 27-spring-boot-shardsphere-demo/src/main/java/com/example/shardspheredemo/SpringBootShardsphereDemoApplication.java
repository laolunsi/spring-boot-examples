package com.example.shardspheredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(value={"org.apache.shardingsphere", "com.example.shardspheredemo.dao"})
@SpringBootApplication
public class SpringBootShardsphereDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardsphereDemoApplication.class, args);
    }

}
