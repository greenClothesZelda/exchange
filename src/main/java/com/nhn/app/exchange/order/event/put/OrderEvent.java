package com.nhn.app.exchange.order.event.put;

//TODO EventPublisher를 이용해서 비동기적으로 처리하기

import com.nhn.app.exchange.front.response.OrderResponse;
import com.nhn.app.exchange.order.dto.OrderDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CompletableFuture;

public class OrderEvent extends ApplicationEvent {
    @Getter
    private final OrderDTO orderDTO;
    private final CompletableFuture<OrderResponse.ResponseCode> future;

    public OrderEvent(Object source, OrderDTO orderDTO) {
        super(source);
        this.orderDTO = orderDTO;
        this.future = null;
    }

    public OrderEvent(Object source, OrderDTO orderDTO, CompletableFuture<OrderResponse.ResponseCode> future) {
        super(source);
        this.orderDTO = orderDTO;
        this.future = future;
    }

    public void complete(OrderResponse.ResponseCode result) {
        if (future != null) {
            future.complete(result);
        }
    }
}
