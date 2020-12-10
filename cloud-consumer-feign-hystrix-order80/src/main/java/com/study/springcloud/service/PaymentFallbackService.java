package com.study.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String payment_OK(int id) {
        return "payment_OK的兜底方法";
    }

    @Override
    public String payment_timeout(int id) {
        return "另一个兜底方法";
    }
}
