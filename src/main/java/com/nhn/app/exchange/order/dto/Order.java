package com.nhn.app.exchange.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {
    public enum OrderType {
        BUY, SELL
    }

    private int orderId;
    private int productId;
    private int price;
    private int amount;
    private OrderType orderType;
    private LocalDateTime createdAt;

    public Order(){}

    public Order(int orderId, int productId, int price, int amount, OrderType orderType, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
        this.orderType = orderType;
        this.createdAt = createdAt;
    }

    //createdAt must be in ISO-8601 format (e.g., "2023-10-05T14:48:00")
    public Order(int orderId, int productId, int price, int amount, OrderType orderType, String createdAt){
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
        this.orderType = orderType;
        this.createdAt = LocalDateTime.parse(createdAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", price=" + price +
                ", amount=" + amount +
                ", orderType=" + orderType +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Order order = (Order) obj;

        if (orderId != order.orderId) return false;
        if (productId != order.productId) return false;
        if (price != order.price) return false;
        if (amount != order.amount) return false;
        if (orderType != order.orderType) return false;
        return createdAt != null ? createdAt.equals(order.createdAt) : order.createdAt == null;
    }
}
