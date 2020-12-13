package com.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    String url;

    @RequestMapping("/consumer/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") int id){
        return restTemplate.getForObject(url+"/payment/nacos/"+id,String.class);
    }
}
