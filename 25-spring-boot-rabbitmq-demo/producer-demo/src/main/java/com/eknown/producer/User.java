package com.eknown.producer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/11 13:44
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
