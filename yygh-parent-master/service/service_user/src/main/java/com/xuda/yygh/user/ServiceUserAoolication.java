package com.xuda.yygh.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-13 18:45
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.xuda")
@EnableDiscoveryClient  //服务注册与发现
@EnableFeignClients(basePackages = "com.xuda")
public class ServiceUserAoolication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserAoolication.class,args);
    }
}
