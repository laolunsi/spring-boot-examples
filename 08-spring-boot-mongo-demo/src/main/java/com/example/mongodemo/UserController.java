package com.example.mongodemo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/6 17:11
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "")
    public JsonResult list() {
        List<User> userList = mongoTemplate.findAll(User.class, "user");
        return new JsonResult(true, userList);
    }

    @PostMapping(value = "")
    public JsonResult add(User user) {
        String msg = verifySaveForm(user);
        if (!StringUtils.isEmpty(msg)) {
            return new JsonResult(false, msg);
        }

        if (user.getId() == null) {
            user.setCreateTime(new Date());
            user.setLastUpdateTime(new Date());
            User newUser = mongoTemplate.insert(user, "user");
            return new JsonResult(true, newUser);
        } else {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(user.getId()));

            Update update = new Update();
            update.set("name", user.getName());
            update.set("password", user.getPassword());
            update.set("address", user.getAddress());
            update.set("last_update_time", new Date());

            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "user");
            return new JsonResult(true, updateResult);
        }
    }

    @DeleteMapping(value = "{id}")
    public JsonResult delete(@PathVariable String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class, "user");
        return new JsonResult(true, deleteResult);
    }

    // private methods

    private String verifySaveForm(User user) {
        if (user == null || StringUtils.isEmpty(user.getName())) {
            return "用户名不能为空";
        } else if (user.getPassword() == null) {
            return "密码不能为空";
        }

        return null;
    }
}
