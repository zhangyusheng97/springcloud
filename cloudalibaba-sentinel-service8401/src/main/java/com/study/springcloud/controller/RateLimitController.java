package com.study.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @RequestMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommentResult byResource(){
        Payment payment = new Payment();
        payment.setId(2020L);
        payment.setSerial("serial001");
        return new CommentResult(200,"按资源名限流测试OK",payment);
    }
    public CommentResult handleException(BlockException e){
        return new CommentResult(444,e.getClass().getCanonicalName() + "服务不可用");
    }
}
