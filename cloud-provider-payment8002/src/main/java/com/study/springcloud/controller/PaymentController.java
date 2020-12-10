package com.study.springcloud.controller;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import com.study.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @ResponseBody
    @PostMapping("/payment/add")
    public CommentResult create(@RequestBody Payment payment){
        int result = paymentService.addPayment(payment);
        if (result > 0){
            return new CommentResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else {
            return new CommentResult(444,"插入失败");
        }
    }

    @ResponseBody
    @GetMapping("/payment/get/{id}")
    public CommentResult getPayment(@PathVariable("id") long id){
        Payment payment = paymentService.findPaymentById(id);
        if (payment != null){
            return new CommentResult(200,"查询成功,serverPort:" + serverPort,payment);
        }else {
            return new CommentResult(444,"查询失败");
        }
    }

    @ResponseBody
    @RequestMapping("/payment/lx")
    public String getServerPort(){
        return serverPort;
    }
}
