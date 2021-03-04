package com.example.clickhousedemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @version 1.0
 * @since 2021/3/2
 */
@Configuration
@MapperScan(basePackages = {
        "com.aegis.analysis.clickhousestorage.dao"
})
public class DruidConfig {
    @Resource
    private JdbcParamConfig jdbcParamConfig ;

    @Bean(name = "clickDataSource")
    public DataSource dataSource() throws ClassNotFoundException {
        Class classes = Class.forName("com.alibaba.druid.pool.DruidDataSource");
        DruidDataSource dataSource = (DruidDataSource) DataSourceBuilder
                .create()
                .driverClassName(jdbcParamConfig.getDriverClassName())
                .type(classes)
                .url(jdbcParamConfig.getUrl())
                .username(jdbcParamConfig.getUserName())
                .password(jdbcParamConfig.getPassword())
                .build();
        dataSource.setMaxWait(jdbcParamConfig.getMaxWait());
        dataSource.setValidationQuery(jdbcParamConfig.getValidationQuery());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory clickHouseSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        // 实体 model的 路径 比如 com.order.model
        factory.setTypeAliasesPackage("com.example.clickhousedemo.model");
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        //开启驼峰命名转换
        factory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory.getObject();
    }
}
