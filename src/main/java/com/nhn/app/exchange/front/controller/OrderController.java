package com.nhn.app.exchange.front.controller;

import com.nhn.app.exchange.front.response.OrderResponse;
import com.nhn.app.exchange.order.dto.OrderDTO;
import com.nhn.app.exchange.order.event.OrderEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Integer placeOrder() {
        // TODO 주문을 처리하는 로직 작성
        CompletableFuture<OrderResponse.ResponseCode> future = new CompletableFuture<>();
        OrderDTO orderDTO = new OrderDTO(1,1,1,1, OrderDTO.OrderType.BUY, "2023-10-05T14:48:00");
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
