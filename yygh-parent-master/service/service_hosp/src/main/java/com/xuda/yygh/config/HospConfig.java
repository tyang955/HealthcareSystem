package com.xuda.yygh.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 14:32
 */
@Configuration
@MapperScan("com.xuda.yygh.mapper")
public class HospConfig {
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
