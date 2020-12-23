package com.study.springcloud.service;

import com.study.springcloud.dao.StorageMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    @Autowired
    StorageMapper storageMapper;

    public void decrease(long productId,int count){
        storageMapper.decrease(productId,  count);
    }
}
