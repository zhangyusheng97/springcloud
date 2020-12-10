package com.study.springcloud.controller;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import com.study.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class PaymentController {

    @Autowired
    PaymentFeignService paymentFeignService;

    @ResponseBody
    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id") long id){
        return paymentFeignService.getPayment(id);
    }

    @ResponseBody
    @RequestMapping("/consumer/payment/feign/timeout")
    public String timeOut(){
        return paymentFeignService.timeOut();
    }
}
