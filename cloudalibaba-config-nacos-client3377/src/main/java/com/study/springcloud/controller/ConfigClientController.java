package com.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //支持nacos的动态刷新
public class ConfigClientController {
    @Value("${config.info}")
    String info;

    @RequestMapping("/config/info")
    public String getInfo(){
        return info;
    }
}
