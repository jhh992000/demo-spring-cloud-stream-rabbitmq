package com.example.stock.configuration;

import com.example.stock.domain.Order;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

    @Bean
    Consumer<List<Order>> input() {
        return list -> {
            System.out.println("Received " + list.size());
            list.forEach(order -> {

                //재고조회 및 주문승인처리
                System.out.println(order);

            });
        };
    }

}
