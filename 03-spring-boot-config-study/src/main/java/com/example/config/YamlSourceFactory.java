package com.example.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/11/19 11:05
 */
public class YamlSourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        return new YamlPropertySourceLoader().load(resource.getResource().getFilename()
                , resource.getResource()).get(0);
    }
}
