package com.study.springcloud.service;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "nacos-payment-provider" , fallback = PaymentFallbackService.class)
public interface PaymentService {
    @RequestMapping("/payment/{id}")
    public CommentResult<Payment> paymentSql(@PathVariable("id") long id);
}
