package com.example.consumerdemo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/14 16:53
 */
@RefreshScope // 注意要加上 @RefreshScope 注解，否则即使 RefreshEventListener 检测到配置信息被更新了，下面的 msg 还是会保持之前的值
@RestController
@RequestMapping(value = "test")
public class TestAction {

    @NacosValue("${msg}")
    private String msg; // 用 @NacosValue 获取不到值 （null）

    @Value("${msg}")
    private String vMsg; // 用 @Value 获取到了值

    @GetMapping(value = "")
    public String hello() {
        return msg + ":" + vMsg;
    }
}
