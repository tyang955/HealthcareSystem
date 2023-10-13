package com.xuda.yygh.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-16 13:41
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xuda"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.xuda"})
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
