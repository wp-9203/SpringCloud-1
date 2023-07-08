package com.wp.springcloud.controller;

import com.wp.springcloud.entities.CommonResult;
import com.wp.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;

@RestController
@Slf4j
public class OrderController {

   // 单机模式 public static final String PAYMENT_URL = "http://localhost:8001";
   public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";  //微服务名
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/save")
    public CommonResult<Payment> save(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/save",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")long id){
        log.info("请求进入参数"+id+"请求地址"+PAYMENT_URL+"/payment/get/"+id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getEntityPayment(@PathVariable("id")long id){
        log.info("请求进入参数"+id+"请求地址"+PAYMENT_URL+"/payment/get/"+id);
        ResponseEntity<CommonResult> entity =restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        } else {
          return new CommonResult<>(444,"操作失败");
        }
    }

}
