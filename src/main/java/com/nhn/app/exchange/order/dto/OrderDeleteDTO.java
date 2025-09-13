package com.nhn.app.exchange.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDeleteDTO {
    @JsonProperty("order_id")
    private int orderId;
    @JsonProperty("stock_id")
    private int stockId;

    public OrderDeleteDTO() {
    }

    public OrderDeleteDTO(int orderId, int stockId) {
        this.orderId = orderId;
        this.stockId = stockId;
    }

    @Override
    public String toString() {
        return "OrderDeleteDTO{" +
                "orderId=" + orderId +
                ", stockId=" + stockId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDeleteDTO that = (OrderDeleteDTO) o;

        if (orderId != that.orderId) return false;
        return stockId == that.stockId;
    }
}
