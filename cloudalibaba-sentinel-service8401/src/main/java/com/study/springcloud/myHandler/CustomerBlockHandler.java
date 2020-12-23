package com.study.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;

public class CustomerBlockHandler {
    public static CommentResult handlerException(BlockException e){
        return new CommentResult(444,"按客户自定义的global处理----1");
    }

    public static CommentResult handlerException2(BlockException e){
        return new CommentResult(444,"按客户自定义的global处理------2");
    }
}
