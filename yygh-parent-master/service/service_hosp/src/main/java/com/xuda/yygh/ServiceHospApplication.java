package com.xuda.yygh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * @author ：程序员徐大大
 * @description：TODO
 *
 * @date ：2022-04-02 14:06
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xuda")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xuda")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
