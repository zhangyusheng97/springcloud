package com.study.springcloud.controller;

import com.study.springcloud.entity.CommentResult;
import com.study.springcloud.entity.Payment;
import com.study.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    DiscoveryClient discoveryClient;

    @ResponseBody
    @PostMapping("/payment/add")
    public CommentResult create(@RequestBody Payment payment){
        int result = paymentService.addPayment(payment);
        if (result > 0){
            return new CommentResult(200,"插入数据成功"+",端口号是"+serverPort,result);
        }else {
            return new CommentResult(444,"插入失败");
        }
    }

    @ResponseBody
    @GetMapping("/payment/get/{id}")
    public CommentResult getPayment(@PathVariable("id") long id){
        Payment payment = paymentService.findPaymentById(id);
        if (payment != null){
            return new CommentResult(200,"查询成功,serverPort="+ serverPort,payment);
        }else {
            return new CommentResult(444,"查询失败");
        }
    }

    @ResponseBody
    @RequestMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services){
            System.out.println(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            System.out.println(instance.getHost() + "--" +instance.getPort() + "---" + instance.getServiceId() + " ---" + instance.getInstanceId());
        }
        return discoveryClient;
    }

    @ResponseBody
    @RequestMapping("/payment/lx")
    public String getServerPort(){
        return serverPort;
    }

    @ResponseBody
    @RequestMapping("/payment/feign/timeout")
    public String timeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
