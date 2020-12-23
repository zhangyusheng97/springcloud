package com.study.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import com.study.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    public String serviceUrl = "http://nacos-payment-provider";
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback" , fallback = "handlerFallback") //fallback负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //只配置blockHandler
    @SentinelResource(value = "fallback" ,fallback = "handlerFallback" , blockHandler = "blockHandler")
    public CommentResult<Payment> fallback(@PathVariable("id") long id){
        CommentResult result = restTemplate.getForObject(serviceUrl + "/payment/" + id, CommentResult.class, id);
        if (id == 4){
            throw new IllegalArgumentException("非法参数异常");
        }else if (result.getData() == null){
            throw new NullPointerException("空指针异常=======");
        }
        return result;
    }
    //使用fallback
    public CommentResult<Payment> handlerFallback(long id ,Throwable e){
        Payment payment = new Payment();
        payment.setId(id);
        payment.setSerial("这是一个异常");
        return new CommentResult<>(444,"这是一个兜底的方法 + " + e.getMessage(),payment);
    }

    //使用blockHandler
    public CommentResult<Payment> blockHandler(long id , BlockException e){
        Payment payment = new Payment();
        payment.setId(id);
        payment.setSerial("这是blockHandler的异常");
        return new CommentResult<>(445,"blockHandler的兜底方法"+e.getMessage(),payment);
    }
    //=========================================open Feign=====================================================

    @Resource
    PaymentService paymentService;

    @RequestMapping("/consumer/payment/{id}")
    public CommentResult<Payment> paymentSql(@PathVariable("id") long id){
        return paymentService.paymentSql(id);
    }
}
