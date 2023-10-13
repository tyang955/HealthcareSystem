package com.xuda.yygh.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-13 21:03
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据库源自动配置
@EnableDiscoveryClient   //nacos注册
@ComponentScan(basePackages = "com.xuda")
@EnableAsync //开启异步注解，进行异步发送邮箱信息
public class ServiceMsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmApplication.class, args);
    }
}
