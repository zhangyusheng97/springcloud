package com.study.springcloud.service;

import com.study.springcloud.dao.PaymentMapper;
import com.study.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentMapper paymentMapper;

    public int addPayment(Payment payment){
        return paymentMapper.addPayment(payment);
    }

    public Payment findPaymentById(long id){
        return paymentMapper.selectPaymentById(id);
    }
}
