# 비동기통신 데모 프로젝트

## 프로젝트 구성

- 서브모듈(애플리케이션)을 포함하는 하나의 프로젝트 형태로 구성 (gradle 활용)

## DEMO 를 위한 사전 준비사항

- rabbitmq 설치후 구동된 상태여야함
    ```properties
    spring.rabbitmq.host=localhost
    spring.rabbitmq.port=5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    ```

## 서비스 동작 안내

### 주문서비스
- 서비스를 구동시킨 후 아래의 주소를 호출하여 주문을 요청한다.
- http://127.0.0.1:8080/order?productId=1
- 위 주소가 호출되면 주문요청을 메세지큐에 전송한다.

### 재고서비스
- 메세지큐에 쌓인 주문요청 데이터를 꺼내어 재고조회한 후 주문승인 or 주문취소 처리한다.
- 주문취소 또한 비동기통신으로 주문서비스에 요청한다.
