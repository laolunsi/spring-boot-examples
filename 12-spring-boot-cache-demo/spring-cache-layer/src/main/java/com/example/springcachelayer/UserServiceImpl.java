package com.example.springcachelayer;

import com.github.xiaolyuh.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/24 11:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    //@Cacheable(value = "user", key = "#userId")
    @Cacheable(value = "user", key = "#userId",
        firstCache = @FirstCache(expireTime = 5, timeUnit = TimeUnit.MINUTES),
        secondaryCache = @SecondaryCache(expireTime = 10, preloadTime = 3, forceRefresh = true, isAllowNullValue = true, timeUnit = TimeUnit.MINUTES))
    public User findById(Integer userId) {
        return userDAO.findById(userId);
    }

    @Override
    //@CachePut(value = "user", key = "#user.id", condition = "#user.id != null")
    @CachePut(value = "user", key = "#user.id",
            firstCache = @FirstCache(expireTime = 5, timeUnit = TimeUnit.MINUTES),
            secondaryCache = @SecondaryCache(expireTime = 10, preloadTime = 3, forceRefresh = true, isAllowNullValue = true, timeUnit = TimeUnit.MINUTES))
    public User save(User user) {
        user.setUpdateTime(new Date());
        userDAO.save(user);
        return userDAO.findById(user.getId());
    }

    @Override
    //@CacheEvict(value = "user", key = "#userId")
    @CacheEvict(value = "user", key = "#userId")
    public boolean deleteById(Integer userId) {
        return userDAO.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
