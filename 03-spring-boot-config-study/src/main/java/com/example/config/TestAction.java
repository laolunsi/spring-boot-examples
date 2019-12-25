package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 10:50
 */
@RestController
@RequestMapping(value = "test")
public class TestAction {

    // from properties

    @Autowired
    private TestConfiguration testConfiguration;
    @Autowired
    /*@Qualifier("testYamlConfiguration")*/
    private TestYamlConfiguration ymlConfiguration;

    // from yaml

    @GetMapping(value = "config")
    public String test() {
        return testConfiguration.getName() + "<br/>" + testConfiguration.getTime();
    }

    @GetMapping(value = "yml")
    public String testYml() {
        return "yml config: <br/>" + ymlConfiguration.getName() + "<br/>" + ymlConfiguration.getTime();
    }
}
