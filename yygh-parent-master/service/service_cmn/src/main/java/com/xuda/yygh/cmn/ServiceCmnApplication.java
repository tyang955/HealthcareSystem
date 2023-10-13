package com.xuda.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-05 20:54
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xuda")//这里扫描comment的模块下的，不需要写，写了回报错
@EnableDiscoveryClient
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class,args);
    }
}
