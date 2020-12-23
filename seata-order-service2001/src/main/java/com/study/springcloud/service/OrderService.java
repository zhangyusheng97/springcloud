package com.study.springcloud.service;

import com.study.springcloud.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;

public interface OrderService {
    void create(Order order);
}
