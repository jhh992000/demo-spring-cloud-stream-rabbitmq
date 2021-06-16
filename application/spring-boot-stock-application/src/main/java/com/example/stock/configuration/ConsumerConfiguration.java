package com.example.stock.configuration;

import com.example.stock.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

    @Autowired
    private RabbitTemplate template;

    /**
     * 배치모드 true 인 경우
     */
    @Bean
    Consumer<List<Order>> myOrder() {
        return list -> {
            System.out.println("주문요청 수신 " + list.size());
            list.forEach(order -> {

                //재고조회 및 주문승인처리
                System.out.println(order);

                //특정주문 취소처리
                if (order.getOrderId()%10 == 0) {
                    cancelOrder(order);
                }

            });
        };
    }

    private void cancelOrder(Order order) {
        String message = null;
        try {
            message = new ObjectMapper().writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------");
        System.out.println("주문취소요청 전송 : " + message);
        System.out.println("-------------------------------------");

        //rabbitmq로 메세지 전송
        template.convertAndSend("myOrderCancel-in-0.myOrderGroup", message);
    }

    /* 배치모드 false 인 경우
    @Bean
    Consumer<String> input() {
        return message -> {
            //재고조회 및 주문승인처리
            System.out.println(message);
        };
    }
     */

}
