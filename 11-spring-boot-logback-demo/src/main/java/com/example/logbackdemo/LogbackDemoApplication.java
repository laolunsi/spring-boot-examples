package com.example.logbackdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogbackDemoApplication {

    private static Logger logger = LoggerFactory.getLogger(LogbackDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogbackDemoApplication.class, args);
        logger.debug("项目启动完毕...");
        logger.info("项目启动完毕...");
        logger.warn("项目启动完毕...");
        logger.error("项目启动完毕...");
    }

}
