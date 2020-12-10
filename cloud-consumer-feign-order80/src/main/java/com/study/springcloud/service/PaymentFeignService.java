package com.study.springcloud.service;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @ResponseBody
    @GetMapping("/payment/get/{id}")
    public CommentResult getPayment(@PathVariable("id") long id);

    @ResponseBody
    @RequestMapping("/payment/feign/timeout")
    public String timeOut();
}
