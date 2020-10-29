package com.example.shardspheredemo.controller;

import com.example.shardspheredemo.bean.Blog;
import com.example.shardspheredemo.dao.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/28 9:46
 */
@RestController
@RequestMapping("blog")
public class BlogAction {

    @Autowired
    private BlogDAO blogDAO;

    @PostMapping("")
    public Object save(Blog blog) {
        if (blog == null || StringUtils.isEmpty(blog.getTitle())) {
            return "标题不能为空";
        } else if (StringUtils.isEmpty(blog.getContent())) {
            return "正文不能为空";
        } else if (blog.getUserId() == null) {
            return "请设置 userId";
        }

        blog.setUpdateTime(new Date());
        if (blog.getId() == null) {
            return blogDAO.insert(blog);
        } else {
            return blogDAO.update(blog);
        }
    }

    @GetMapping("list")
    public Object list(Integer currentIndex, Integer limit) {
        if (currentIndex == null || currentIndex < 0 || limit == null || limit <= 0) {
            currentIndex = 0;
            limit = 10;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", blogDAO.count());
        map.put("list", blogDAO.find(currentIndex, limit));
        return map;
    }

    @GetMapping(value = "{id}")
    public Object findById(@PathVariable Long id) {
        return blogDAO.findById(id);
    }

    @GetMapping(value = "userId/{userId}")
    public Object findByUserId(@PathVariable Long userId) {
        return blogDAO.findByUserId(userId);
    }
}
