package com.study.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderHystrixController {
    @Autowired
    PaymentHystrixService paymentHystrixService;

    @RequestMapping("/consumer/payment/ok/{id}")
    public String payment_OK(@PathVariable("id") int id){
        return paymentHystrixService.payment_OK(id);
    }

    @RequestMapping("/consumer/payment/timeout/{id}")
    @HystrixCommand(fallbackMethod = "fallback" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String payment_timeout(@PathVariable("id") int id){
        return paymentHystrixService.payment_timeout(id);
    }

    public String fallback(@PathVariable("id") int id){
        return "我是消费者80，暂时无法访问服务端" + id;
    }

}
