package com.study.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StorageMapper {
    void decrease(@Param("productId") long productId,@Param("count") int count);
}
