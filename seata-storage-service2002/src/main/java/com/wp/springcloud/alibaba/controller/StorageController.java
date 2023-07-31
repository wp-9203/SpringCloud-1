package com.wp.springcloud.alibaba.controller;

import com.wp.springcloud.alibaba.entities.CommonResult;
import com.wp.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        log.info("-----storage--开始扣减库存----");
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功");
    }
}
