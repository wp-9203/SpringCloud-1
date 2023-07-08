package com.wp.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wp.springcloud.service.PaymentFeignHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod") //全局服务降级
public class OrderHystrixController {
    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand   //此处没有指出具体的兜底方法，使用全局怕配置的兜底方法
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        log.info("请求进入ID："+id);
        int age = 10/0;
        return paymentFeignHystrixService.paymentInfo_ok(id);
    }
    public String payment_global_fallbackMethod(){
        return "系统异常，请稍后重试";
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        //int age = 10/0;
        String result = paymentFeignHystrixService.paymentInfo_timeout(id);
        return result;
    }

    public String paymentInfo_timeoutHandler(@PathVariable("id") Integer id){
        return "对方系统繁忙，请求10s后重试或者系统运行出错,id="+id;
    }


}
