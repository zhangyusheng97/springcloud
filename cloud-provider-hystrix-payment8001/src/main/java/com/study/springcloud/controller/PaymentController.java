package com.study.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.study.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    String serverPort;

    @RequestMapping("/payment/ok/{id}")
    public String payment_OK(@PathVariable("id") int id){
        return paymentService.payment_OK(id);
    }

    @RequestMapping("/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") int id){
        return paymentService.payment_Timeout(id);
    }

    //--------------------------------------服务熔断-------------------------------------------------------
    @RequestMapping("/payment/circuit/{id}")
    public String payment_circuit(@PathVariable("id") int id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
