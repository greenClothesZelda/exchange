package com.nhn.app.exchange.order.event.delete;

import com.nhn.app.exchange.front.response.OrderCancelResponse;
import com.nhn.app.exchange.order.dto.OrderDeleteDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CompletableFuture;

public class OrderDeleteEvent extends ApplicationEvent {
    @Getter
    private final OrderDeleteDTO orderDeleteDTO;
    private final CompletableFuture<OrderCancelResponse.ResponseCode> future;

    public OrderDeleteEvent(Object source, OrderDeleteDTO orderDeleteDTO) {
        super(source);
        this.orderDeleteDTO = orderDeleteDTO;
        this.future = null;
    }

    public OrderDeleteEvent(Object source, OrderDeleteDTO orderDeleteDTO, CompletableFuture<OrderCancelResponse.ResponseCode> future) {
        super(source);
        this.orderDeleteDTO = orderDeleteDTO;
        this.future = future;
    }

    public void complete() {
        if (future != null) {
            future.complete(OrderCancelResponse.ResponseCode.SUCCESS);
        }
    }

    public void complete(OrderCancelResponse.ResponseCode result) {
        if (future != null) {
            future.complete(result);
        }
    }
}
