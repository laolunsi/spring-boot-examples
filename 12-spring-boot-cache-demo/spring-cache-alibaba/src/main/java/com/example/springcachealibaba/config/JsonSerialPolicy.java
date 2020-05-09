package com.example.springcachealibaba.config;

import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.CacheValueHolder;
import com.alicp.jetcache.anno.SerialPolicy;

import java.util.function.Function;

/**
 * 自定义的 Json 序列化策略类
 * 注意：这里不是直接将 byte[] 和 Object 进行互换，而是采用了一个 CacheValueHolder 的中间类型
 * 主要原因在于 JetCache 的缓存对象是使用 CacheValueHolder 这个类来封装的。强制类型转换会出现异常。
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/27 13:51
 */
public class JsonSerialPolicy implements SerialPolicy {

    @Override
    public Function<Object, byte[]> encoder() {
        return  o -> {
            if (o != null) {
                CacheValueHolder cacheValueHolder = (CacheValueHolder) o;
                Object realObj = cacheValueHolder.getValue();
                String objClassName = realObj.getClass().getName();
                // 为防止出现 Value 无法强转成指定类型对象的异常，这里生成一个 JsonCacheObject 对象，保存目标对象的类型（比如 User）
                JsonCacheObject jsonCacheObject = new JsonCacheObject(objClassName, realObj);
                cacheValueHolder.setValue(jsonCacheObject);
                return JSONObject.toJSONString(cacheValueHolder).getBytes();
            }
            return new byte[0];
        };
    }

    @Override
    public Function<byte[], Object> decoder() {
        return bytes -> {
            if (bytes != null) {
                String str = new String(bytes);
                CacheValueHolder cacheValueHolder = JSONObject.parseObject(str, CacheValueHolder.class);
                JSONObject jsonObject = JSONObject.parseObject(str);
                // 首先要解析出 JsonCacheObject，然后获取到其中的 realObj 及其类型
                JSONObject jsonOfMy = jsonObject.getJSONObject("value");
                if (jsonOfMy != null) {
                    JSONObject realObjOfJson = jsonOfMy.getJSONObject("realObj");
                    String className = jsonOfMy.getString("className");
                    try {
                        Object realObj = realObjOfJson.toJavaObject(Class.forName(className));
                        cacheValueHolder.setValue(realObj);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                return cacheValueHolder;
            }
            return null;
        };
    }
}
