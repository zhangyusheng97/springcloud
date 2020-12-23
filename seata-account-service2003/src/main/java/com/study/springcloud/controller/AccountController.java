package com.study.springcloud.controller;

import com.study.springcloud.entity.CommonResult;
import com.study.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decrease(long userId, BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣款成功");
    }
}
