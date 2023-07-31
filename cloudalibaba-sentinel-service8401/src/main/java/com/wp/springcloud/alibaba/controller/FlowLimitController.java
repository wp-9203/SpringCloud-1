package com.wp.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        log.info(Thread.currentThread().getName()+"-----TestA");
        return "......TestA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"-----TestB");
        return "......TestB";
    }

    @GetMapping("/testC")
    public String testC(){
        log.info(Thread.currentThread().getName()+"-----TestC");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "......TestC";
    }

    @GetMapping("/testD")
    public String testD(){
        log.info(Thread.currentThread().getName()+"-----TestD");
        int age = 10/0;
        return "......TestD";
    }


}
