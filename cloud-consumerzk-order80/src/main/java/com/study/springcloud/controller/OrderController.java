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
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/add")
    @ResponseBody
    public CommentResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/add",payment,CommentResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    @ResponseBody
    public CommentResult<Payment> find(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommentResult.class);
    }

    @GetMapping("/consumer/payment/zk")
    @ResponseBody
    public String paymentInfo(){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zk",String.class);
    }
}
