package com.example.applicationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationDemoApplication {

    public static void main(String[] args) {
        new SpringApplication(ApplicationDemoApplication.class).run(args);
        //SpringApplication.run(ApplicationDemoApplication.class, args);
    }

}
