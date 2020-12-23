package com.study.springcloud.controller;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    String port;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setSerial("1111111111111");
        hashMap.put(1L,payment);
        Payment payment2 = new Payment();
        payment2.setId(2L);
        payment2.setSerial("22222222222");
        hashMap.put(2L,payment2);
        Payment payment3 = new Payment();
        payment3.setId(3L);
        payment3.setSerial("3333333333333");
        hashMap.put(3L,payment3);
    }

    @RequestMapping("/payment/{id}")
    public CommentResult<Payment> paymentSql(@PathVariable("id") long id){
        Payment payment = hashMap.get(id);
        return new CommentResult(200,"from sql,serverPore = " + port,payment);
    }
}
