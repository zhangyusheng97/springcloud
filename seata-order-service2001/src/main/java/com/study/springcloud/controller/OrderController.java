package com.study.springcloud.controller;

import com.study.springcloud.entity.CommonResult;
import com.study.springcloud.entity.Order;
import com.study.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }
}
