package com.example.springcachealibaba.config;

import com.alicp.jetcache.anno.support.SpringConfigProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/27 13:56
 */
@Configuration
public class JetCacheConfig {

    // 关于 value 的序列化，可以从以下方案中任选其一

    /**
     * 声明一个 json 序列化策略
     * 在 @Cached 注解中的 serialPolicy 属性中声明：bean:xxx 即可
     * 如：@Cached( name = "user:", key = "#userId", serialPolicy = "bean:jsonPolicy")
     * @return
     */
    @Bean(name = "jsonPolicy")
    public JsonSerialPolicy jsonSerializerPolicy() {
        return new JsonSerialPolicy();
    }

    /**
     * 自定义一个序列化器，覆盖默认配置
     * @return
     */
    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider() {
            @Override
            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
                if (valueDecoder.equalsIgnoreCase("myJson")) {
                    return new JsonSerialPolicy().decoder();
                }
                return super.parseValueDecoder(valueDecoder);
            }

            @Override
            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
                if (valueEncoder.equalsIgnoreCase("myJson")) {
                    return new JsonSerialPolicy().encoder();
                }
                return super.parseValueEncoder(valueEncoder);
            }

        };
    }
}
