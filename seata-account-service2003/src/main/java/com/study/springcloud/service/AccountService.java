package com.study.springcloud.service;

import com.study.springcloud.dao.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;

    public void decrease(long userId, BigDecimal money){
        //模拟超时
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountMapper.decrease(userId, money);
    }
}
