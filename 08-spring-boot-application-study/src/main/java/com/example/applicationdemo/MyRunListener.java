package com.example.applicationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-24 18:55
 */
public class MyRunListener implements SpringApplicationRunListener {

    // 注意要加上这个构造器，两个参数都不能少，否则启动会报错，报错的详情可以看这个类的最下面
    public MyRunListener(SpringApplication springApplication, String[] args) {

    }

    @Override
    public void starting() {
        System.out.println("MyRunListener: 程序开始启动");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    // 注意要加上构造器，否则会报错：
    // Exception in thread "main" java.lang.IllegalArgumentException: Cannot instantiate interface org.springframework.boot.SpringApplicationRunListener : com.example.applicationdemo.MyRunListener
    //	at org.springframework.boot.SpringApplication.createSpringFactoriesInstances(SpringApplication.java:439)
    //	at org.springframework.boot.SpringApplication.getSpringFactoriesInstances(SpringApplication.java:421)
    //	at org.springframework.boot.SpringApplication.getRunListeners(SpringApplication.java:410)
    //	at org.springframework.boot.SpringApplication.run(SpringApplication.java:301)
    //	at com.example.applicationdemo.ApplicationDemoApplication.main(ApplicationDemoApplication.java:10)
    //Caused by: java.lang.NoSuchMethodException: com.example.applicationdemo.MyRunListener.<init>(org.springframework.boot.SpringApplication, [Ljava.lang.String;)
    //	at java.lang.Class.getConstructor0(Class.java:3082)
    //	at java.lang.Class.getDeclaredConstructor(Class.java:2178)
    //	at org.springframework.boot.SpringApplication.createSpringFactoriesInstances(SpringApplication.java:434)
    //	... 4 more
}
