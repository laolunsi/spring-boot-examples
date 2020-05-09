package com.example.springcachealibaba;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/24 13:55
 */
@Service
public class UserDAOImpl implements UserDAO {

    private static ConcurrentHashMap<Integer, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User findById(Integer id) {
        return userMap.get(id);
    }

    @Override
    public boolean save(User user) {
        userMap.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        userMap.remove(id);
        return true;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }
}
