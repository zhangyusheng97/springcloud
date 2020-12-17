package com.study.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @RequestMapping("/testA")
    public String testA(){
        return "test ----A";
    }

    @RequestMapping("/testB")
    public String testB(){
        return "test-----B";
    }
}
