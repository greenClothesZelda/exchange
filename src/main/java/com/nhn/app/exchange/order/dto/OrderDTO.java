package com.nhn.app.exchange.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    public enum OrderType {
        BUY, SELL
    }

    private int orderId;
    private int productId;
    private int price;
    private int amount;
    private OrderType orderType;
    private LocalDateTime createdAt;

    public OrderDTO(){}

    public OrderDTO(int orderId, int productId, int price, int amount, OrderType orderType, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
        this.orderType = orderType;
        this.createdAt = createdAt;
    }

    //createdAt must be in ISO-8601 format (e.g., "2023-10-05T14:48:00")
    public OrderDTO(int orderId, int productId, int price, int amount, OrderType orderType, String createdAt){
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

        OrderDTO orderDTO = (OrderDTO) obj;

        if (orderId != orderDTO.orderId) return false;
        if (productId != orderDTO.productId) return false;
        if (price != orderDTO.price) return false;
        if (amount != orderDTO.amount) return false;
        if (orderType != orderDTO.orderType) return false;
        return createdAt != null ? createdAt.equals(orderDTO.createdAt) : orderDTO.createdAt == null;
    }
}
