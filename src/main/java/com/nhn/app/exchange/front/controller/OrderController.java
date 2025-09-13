package com.nhn.app.exchange.front.controller;

import com.nhn.app.exchange.front.response.OrderResponse;
import com.nhn.app.exchange.order.dto.OrderDTO;
import com.nhn.app.exchange.order.event.OrderEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/market/order")
@Component
public class OrderController {
    private final ApplicationEventPublisher eventPublisher;
    public OrderController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/")
    public Integer placeOrder(@RequestBody OrderDTO orderDTO) {
        // TODO 주문을 처리하는 로직 작성
        CompletableFuture<OrderResponse.ResponseCode> future = new CompletableFuture<>();
        eventPublisher.publishEvent(new OrderEvent(this, orderDTO, future));
        try {
            OrderResponse.ResponseCode result = future.get();
            return result.getCode();
        }  catch (ExecutionException e) {
            return OrderResponse.ResponseCode.INTERNAL_SERVER_ERROR.getCode();
        } catch (InterruptedException e) {
            return OrderResponse.ResponseCode.INTERNAL_SERVER_ERROR.getCode();
        }
    }
}
