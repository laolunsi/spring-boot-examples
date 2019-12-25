package com.example.paramstime;

import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-12-02 22:08
 */
public class User {
    private String name;
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
