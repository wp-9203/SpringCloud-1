package com.wp.springcloud.service;

import com.wp.springcloud.entities.CommonResult;
import com.wp.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")  //value的值是 需要调用的服务端的微服务的名称
public interface PaymentFeignService {
    @GetMapping(value="/payment/get/{id}") // 需要调用的接口的 访问路径(对应服务提供者的controller中方法的访问路径一致)
    CommonResult<Payment> getPaymentById(@PathVariable("id") long id);
    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout();
}
