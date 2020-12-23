package com.study.springcloud.service;

import com.study.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage-service")
public interface StorageService {
    //减少库存
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") long productId, @RequestParam("count")int count);
}
