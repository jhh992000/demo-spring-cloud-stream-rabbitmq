package com.example.order.controller;

import com.example.core.util.CommonUtils;
import com.example.order.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate template;

    private long orderCount = 0L;

    @GetMapping("/order")
    public String order(Order order) throws Exception {

        order.setOrderId(++orderCount);

        if (CommonUtils.isNull(order.getProductId())) {
            return "Please enter product id. [ parameter : productId ]";
        }

        String message = new ObjectMapper().writeValueAsString(order);
        System.out.println("주문요청 : " + message);

        //rabbitmq로 메세지 전송
        template.convertAndSend("myOrder-in-0.myOrderGroup", message);

        return "Order Complete : " + message;
    }

}
