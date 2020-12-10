package com.study.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @RequestMapping("/payment/ok/{id}")
    public String payment_OK(@PathVariable("id") int id);

    @RequestMapping("/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") int id);
}
