package com.study.springcloud.service.impl;

import com.study.springcloud.entity.Order;
import com.study.springcloud.mapper.OrderMapper;
import com.study.springcloud.service.AccountService;
import com.study.springcloud.service.OrderService;
import com.study.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    StorageService storageService;


    @Override
    @GlobalTransactional(name = "orderService",rollbackFor = Exception.class)
    public void create(Order order) {
        System.out.println("-------------开始新建订单---------------");
        orderMapper.create(order);
        System.out.println("----------------订单微服务开始，进行库存减少----------");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("---------库存扣减结束，开始账户操作--------------");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("-----------扣钱结束，修改订单状态-------------");
        orderMapper.update(order.getUserId(),0);
        System.out.println("----------订单结束-----------");
    }
}
