package com.wp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Order80Mainzk {
    public static void main(String[] args) {
        SpringApplication.run(Order80Mainzk.class,args);
    }
}
