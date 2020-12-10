package com.study.springcloud.controller;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import com.study.springcloud.lb.MyLB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class OrderController {
   // public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MyLB myLB;
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/add")
    @ResponseBody
    public CommentResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/add",payment,CommentResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    @ResponseBody
    public CommentResult<Payment> find(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommentResult.class);
    }

    @GetMapping("/consumer/payment/ribbon/get/{id}")
    @ResponseBody
    public CommentResult<Payment> get(@PathVariable("id") long id){
        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
        return entity.getBody();
    }

    @ResponseBody
    @RequestMapping("/consumer/payment/lx")
    public String getPaymentLX(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0 ){
            return null;
        }
        ServiceInstance instance = myLB.instances(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/lx",String.class);
    }
}
