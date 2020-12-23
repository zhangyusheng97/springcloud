package com.study.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface AccountMapper {
    void decrease(@Param("userId") long userId,@Param("money") BigDecimal money);
}
