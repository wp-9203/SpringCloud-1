package com.wp.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentFeignHystrixService{

    @Override
    public String paymentInfo_ok(Integer id) {
        return "对方系统繁忙，请求10s后重试或者系统运行出错";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "对方系统繁忙，请求10s后重试或者系统运行出错";
    }
}
