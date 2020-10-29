package com.example.shardspheredemo.bean;

import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/27 15:48
 */
public class User {
    private Long userId;
    private String name;
    private String password;
    private Date createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
