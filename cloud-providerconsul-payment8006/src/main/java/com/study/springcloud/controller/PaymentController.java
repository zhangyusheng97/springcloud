package com.study.springcloud.controller;

import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @ResponseBody
    @RequestMapping("/payment/consul")
    public String paymentZK(){
        return "zookeeper:" + serverPort + UUID.randomUUID().toString();
    }

}
