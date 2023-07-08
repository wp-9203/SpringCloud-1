package com.wp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignOrder80Main {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrder80Main.class,args);
    }
}
