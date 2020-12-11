package com.study.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.study.springcloud.service.IMessageProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@EnableBinding(Source.class)
public class MessageProviderServiceImpl implements IMessageProviderService {

    @Autowired
    MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("------------" + serial + "----------------------");
        return null;
    }
}
