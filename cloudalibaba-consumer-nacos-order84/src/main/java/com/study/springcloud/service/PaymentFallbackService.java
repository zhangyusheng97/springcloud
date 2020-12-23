package com.study.springcloud.service;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommentResult<Payment> paymentSql(long id) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setSerial("出错了");
        return new CommentResult<>(4443444,"服务降级返回",payment);
    }
}
