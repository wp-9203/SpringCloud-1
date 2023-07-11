package com.wp.springcloud.controller;

import com.wp.springcloud.service.ImessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private ImessageProvider imessageProvider;
    @GetMapping("/sendMessage")
    public String send(){
        return imessageProvider.send();
    }

}
