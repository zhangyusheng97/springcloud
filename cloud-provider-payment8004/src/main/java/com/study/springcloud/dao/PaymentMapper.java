package com.study.springcloud.dao;

import com.study.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentMapper {
    public int addPayment(Payment payment);
    public Payment selectPaymentById(long id);
}
