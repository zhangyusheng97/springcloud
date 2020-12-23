package com.study.springcloud.service;

import com.study.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("seata-account-service")
public interface AccountService {
    //减少账户
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") long userId, @RequestParam("money")BigDecimal money);
}
