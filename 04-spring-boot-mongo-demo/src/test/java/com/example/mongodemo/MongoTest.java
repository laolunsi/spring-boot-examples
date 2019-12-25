package com.example.mongodemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/6 17:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test1() {
        List<User> userList = mongoTemplate.findAll(User.class);
        if (userList != null && userList.size() > 0) {
            userList.forEach(user -> {
                System.out.println(user.toString());
            });
        }
    }

}
