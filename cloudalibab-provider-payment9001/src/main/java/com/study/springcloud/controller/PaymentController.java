package com.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") int id){
        return "nacos注册成功：port："+port+",id是" + id;
    }
}
