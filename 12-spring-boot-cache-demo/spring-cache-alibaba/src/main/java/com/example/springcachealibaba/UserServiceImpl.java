package com.example.springcachealibaba;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
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

    // 下面为使用 AOP 来缓存数据的示例

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Override
    @Cached(name = "user:", key = "#userId", expire = 1000)
    //@Cached( name = "user:", key = "#userId", serialPolicy = "bean:jsonPolicy")
    public User findById(Integer userId) {
        System.out.println("userId: " + userId);
        return userDAO.findById(userId);
    }

    @Override
    @CacheUpdate(name = "user:", key = "#user.id", value = "#user")
    public User save(User user) {
        user.setUpdateTime(new Date());
        boolean res = userDAO.save(user);
        if (res) {
            return userService.findById(user.getId());
        }
        return null;
    }

    @Override
    @CacheInvalidate(name = "user:", key = "#userId")
    public boolean deleteById(Integer userId) {
        return userDAO.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    // 下面的示例为使用 @CreateCache 注解创建 Cache 对象来缓存数据的示例

    /*@CreateCache(name = "user:", expire = 5, timeUnit = TimeUnit.MINUTES)
    private Cache<Integer, User> userCache;

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findById(Integer userId) {
        User user = userCache.get(userId);
        if (user == null || user.getId() == null) {
            user = userDAO.findById(userId);
        }
        return user;
    }

    @Override
    public User save(User user) {
        user.setUpdateTime(new Date());
        userDAO.save(user);
        user = userDAO.findById(user.getId());

        // cache
        userCache.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean deleteById(Integer userId) {
        userCache.remove(userId);
        return userDAO.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }*/
}
