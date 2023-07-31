package com.wp.springcloud.alibaba.controller;

import com.wp.springcloud.alibaba.entities.CommonResult;
import com.wp.springcloud.alibaba.service.AccountSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountSerivce accountSerivce;
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money){
        accountSerivce.descrease(userId,money);
        return  new CommonResult(200,"账户扣减成功");
    }
}
