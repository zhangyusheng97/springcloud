package com.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableBinding(Sink.class)
public class ReceiveMessageController {
    @Value("${server.port}")
    String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("接收到的消息是：" + message.getPayload() + "-----port:" + port);
    }
}
