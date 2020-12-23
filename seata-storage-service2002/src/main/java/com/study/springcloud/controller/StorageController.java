package com.study.springcloud.controller;

import com.study.springcloud.entity.CommonResult;
import com.study.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    StorageService storageService;

    @PostMapping("/storage/decrease")
    public CommonResult decrease(long productId,int count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"减库存成功");
    }
}
