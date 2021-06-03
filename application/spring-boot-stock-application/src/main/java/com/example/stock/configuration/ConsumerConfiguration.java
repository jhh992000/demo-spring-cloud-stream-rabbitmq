package com.example.stock.configuration;

import com.example.stock.domain.Order;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

    /**
     * 배치모드 true 인 경우
     */
    @Bean
    Consumer<List<Order>> myOrder() {
        return list -> {
            System.out.println("Received " + list.size());
            list.forEach(order -> {

                //재고조회 및 주문승인처리
                System.out.println(order);

            });
        };
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
