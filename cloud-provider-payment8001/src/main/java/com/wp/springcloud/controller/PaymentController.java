package com.wp.springcloud.controller;


import com.wp.springcloud.entities.CommonResult;
import com.wp.springcloud.entities.Payment;
import com.wp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("********" + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;

    }

    @PostMapping(value = "/payment/save")
    public CommonResult save(@RequestBody Payment payment) {
        log.info("入参" + payment);
        int result = paymentService.save(payment);
        log.info("******插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "数据插入成功" + serverPort);
        } else {
            return new CommonResult(444, "数据插入失败", null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        log.info("请求进入参数" + id);
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "数据查询成功" + serverPort, payment);
        } else {
            return new CommonResult(400, "没有对应记录，查询ID+" + id, null);
        }
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

}
