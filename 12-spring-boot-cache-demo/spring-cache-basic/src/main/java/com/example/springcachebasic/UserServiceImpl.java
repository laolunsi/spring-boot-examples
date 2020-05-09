package com.example.springcachebasic;

import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    @Cacheable(value = "user", key = "#userId")
    public User findById(Integer userId) {
        return userDAO.findById(userId);
    }

    @Override
    @CachePut(value = "user", key = "#user.id", condition = "#user.id != null")
    public User save(User user) {
        user.setUpdateTime(new Date());
        userDAO.save(user);
        return userDAO.findById(user.getId());
    }

    @Override
    @CacheEvict(value = "user", key = "#userId")
    public boolean deleteById(Integer userId) {
        return userDAO.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
