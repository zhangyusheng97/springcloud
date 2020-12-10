package com.study.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("--------------进来了--------------");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null){
            System.out.println("---------用户名不存在-----------");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        System.out.println("-----------符合要求---------");
        return chain.filter(exchange);
    }

    //加载过滤器的顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
