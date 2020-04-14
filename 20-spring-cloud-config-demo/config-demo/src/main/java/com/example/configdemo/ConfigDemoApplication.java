package com.example.configdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigDemoApplication.class, args);
    }

}
