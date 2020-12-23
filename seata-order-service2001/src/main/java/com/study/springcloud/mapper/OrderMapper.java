package com.study.springcloud.mapper;

import com.study.springcloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    //新建订单
    public abstract int create(Order order);
    //修改订单状态
    public abstract int update(@Param("userId") long userId, @Param("status") int status);
}
