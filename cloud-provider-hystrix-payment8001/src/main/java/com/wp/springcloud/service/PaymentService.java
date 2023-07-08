package com.wp.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     *
     * @param id
     * @return
     */
    public String paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_ok,id："+id;
    }

    /**
     *  fallbackMethod 设置兜底的方法，
     *  commandProperties 自身调用超时时间峰值
     * @param id
     * @return
     */
   /* @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })*/
    public String paymentInfo_timeout(Integer id){
        /* 程序异常或出错案例*/
        int age = 10/0;
        /* 超时案例

        try{
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }*/
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_timeout,id："+id ;
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"8001系统繁忙或运行出错，请稍后重试,id："+id+"哭";
    }
    //##############服务降级 end ##################
    /*################## 服务熔断  ###################*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" ,commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value= "60")//失败率达到后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if( id < 0 ){
            throw new RuntimeException("****** id : 不能为负数");
        }
        String serialNumber = IdUtil.randomUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，id = "+id;
    }
}
