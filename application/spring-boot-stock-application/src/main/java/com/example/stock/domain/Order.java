package com.example.stock.domain;

public class Order {

    private Long orderId;
    private Long productId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", productId=" + productId +
            '}';
    }
}
