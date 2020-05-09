package com.example.serviceprovider;

import org.springframework.web.bind.annotation.*;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/27 14:33
 */
@RestController
@RequestMapping(value = "hello")
public class HelloAction {

    @GetMapping(value = "{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello, " + name + ", this is service provider's response.";
    }
}
