package com.study.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String payment_OK(int id){
        return "线程池：" + Thread.currentThread().getName() + "当前的id：" + id +"\t" + "hhh成功了";
    }

    @HystrixCommand(fallbackMethod = "payment_timeout_handler" , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    } )
    public String payment_Timeout(int id){
        //int age = 10 /0 ;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "当前的id：" + id +"\t" + "hhh成功了" + "耗时：" + timeNumber ;
    }

    public String payment_timeout_handler(int id){
        return "线程池：" + Thread.currentThread().getName() + "当前的id：" + id +"\t" + "------暂时无法访问";
    }


    //----------------------------------------服务熔断--------------------------------------------------------------
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value ="true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"), //时间窗口期，时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")  //失败率达到多少之后跳闸
    })//上述设置即为在10秒钟内10次请求，失败率达到百分之60即为失败。
    public String paymentCircuitBreaker(int id){
        if (id<0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号：" + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(int id){
        return "id 不能为负数，请稍后再试";
    }
}
