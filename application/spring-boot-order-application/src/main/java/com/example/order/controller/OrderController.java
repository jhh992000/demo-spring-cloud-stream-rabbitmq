package com.example.order.controller;

import com.example.core.util.CommonUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/order")
    public String order(String productName) {
        if (CommonUtils.isNull(productName)) {
            return "Please enter product name. [ parameter : productName ]";
        }

        //rabbitmq로 데이터 전송
        template.convertAndSend("input-in-0.someGroup", "{\"productName\":\"" + productName + "\"}");

        return "Order Complete : " + productName;
    }

}
