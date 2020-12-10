package com.study.springcloud.controller;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class OrderController {
   // public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://consul-payment-service";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    @ResponseBody
    public String create(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }

}
