package com.example.exceptiondemo.model;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/12/5 15:44
 */
public class JsonResult {

    protected boolean   success;
    protected String    msg;
    protected int       code;
    protected Object    data;

    public JsonResult() {
    }

    public JsonResult(boolean success, String msg) {
        this(success);
        this.msg = msg;
    }

    public JsonResult(boolean success, String msg, int code) {
        this(success, msg);
        this.code = code;
    }

    public JsonResult(boolean success) {
        this();
        this.success = success;
    }

    public JsonResult(boolean success, String msg, Object data) {
        this(success, msg);
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public JsonResult put(String key, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        ((HashMap) data).put(key, value);
        return this;
    }

    public JsonResult putAll(Map<String, Object> m) {
        if (data == null) {
            data = new HashMap<>();
        }
        ((HashMap) data).putAll(m);
        return this;
    }

}

