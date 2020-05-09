package com.example.springcachebasic;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/24 11:23
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
