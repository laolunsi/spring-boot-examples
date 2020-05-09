package com.example.springcachealibaba.config;

/**
 * 缓存对象
 * 为方便进行 JSON 序列化，缓存时将数据添加到 realObj 字段，然后将其类型添加到 className 字段
 * @param <V>
 */
public class JsonCacheObject<V> {

    private String className;
    private V realObj;

    public JsonCacheObject() {
    }

    public JsonCacheObject(String className, V realObj) {
        this.className = className;
        this.realObj = realObj;
    }

    public V getRealObj() {
        return realObj;
    }

    public void setRealObj(V realObj) {
        this.realObj = realObj;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
