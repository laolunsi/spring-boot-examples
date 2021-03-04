package com.example.clickhousedemo.model;

import java.util.Date;

/**
 * @version 1.0
 * @since 2021/3/2
 */
public class UserInfo {

    /**
     * ClickHouse 建表语句：
     * CREATE TABLE test.user (
     * `id` Int16,
     *  `name` String,
     *  `score` Float32,
     *  `score2` Float64,
     *  `state` Int8,
     *  `createTime` DateTime,
     *  `ranks` Array(UInt8)
     * ) ENGINE = MergeTree() ORDER BY id;
     */

    private Integer id; // int16
    private String name; // String
    private Float score; // float16
    private Double score2; // float32
    private Boolean state; // int8
    private Date createTime; // datetime
    private Integer[] ranks; // Array - Array 类型需要进行类型转换
    // 具体转换方法与配置参考 ClickArrayToIntHandler 类与 UserMapper.xml 中关于查询和插入时 ranks 字段的配置

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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer[] getRanks() {
        return ranks;
    }

    public void setRanks(Integer[] ranks) {
        this.ranks = ranks;
    }

    /*    public Object getRanks() {
        return ranks;
    }

    public void setRanks(Object ranks) {
        this.ranks = ranks;
    }

    public int[] getRanksArray() {
        return this.ranks != null ? (int[])this.ranks : null;
    }*/
}

