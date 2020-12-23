package com.study.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    @RequestMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test -----D";
    }

    @RequestMapping("/testHotKey")
    @SentinelResource(value = "testHotKey" , blockHandler = "deal_testHotKey")
    public String testHotKey(String p1,String p2){
        System.out.println(p1 + "--"+p2);
        return "hotKey设置";
    }

    public String deal_testHotKey(String p1, String p2, BlockException e){
        return "------deal_testHotKey,失败了！";
    }
}
