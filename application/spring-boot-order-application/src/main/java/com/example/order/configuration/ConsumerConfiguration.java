package com.example.order.configuration;

import com.example.order.domain.Order;
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
    Consumer<List<Order>> myOrderCancel() {
        return list -> {
            System.out.println("-------------------------------------");
            System.out.println("주문취소요청 수신 " + list.size());
            list.forEach(order -> {

                //주문취소처리
                System.out.println("주문취소 : " + order);

            });
            System.out.println("-------------------------------------");
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
