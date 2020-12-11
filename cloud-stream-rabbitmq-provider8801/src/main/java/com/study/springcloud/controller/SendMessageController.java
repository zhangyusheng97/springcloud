package com.study.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.study.springcloud.service.impl.MessageProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    MessageProviderServiceImpl messageProviderService;

    @RequestMapping("/sendMessage")
    public String sendMessage(){
        return messageProviderService.send();
    }
}
