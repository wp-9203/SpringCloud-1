package com.wp.springcloud.alibaba.service;

import com.wp.springcloud.alibaba.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") long productId, @RequestParam("count") Integer count);
}
