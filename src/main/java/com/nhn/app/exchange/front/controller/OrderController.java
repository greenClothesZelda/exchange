package com.nhn.app.exchange.front.controller;

import com.nhn.app.exchange.front.response.OrderCancelResponse;
import com.nhn.app.exchange.front.response.OrderResponse;
import com.nhn.app.exchange.order.dto.OrderDTO;
import com.nhn.app.exchange.order.dto.OrderDeleteDTO;
import com.nhn.app.exchange.order.event.put.OrderEvent;
import com.nhn.app.exchange.order.event.delete.OrderDeleteEvent;
import org.springframework.context.ApplicationEventPublisher;
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

    @DeleteMapping("/{orderId}/cancel")
    public Integer cancelOrder(@PathVariable Long orderId, @RequestBody OrderDeleteDTO orderDeleteDTO) {
        // TODO 주문 취소를 처리하는 로직 작성
        CompletableFuture<OrderCancelResponse.ResponseCode> future = new CompletableFuture<>();
        eventPublisher.publishEvent(new OrderDeleteEvent(this, orderDeleteDTO, future));

        try{
            OrderCancelResponse.ResponseCode result = future.get();
            return result.getCode();
        } catch (ExecutionException e) {
            return OrderResponse.ResponseCode.INTERNAL_SERVER_ERROR.getCode();
        } catch (InterruptedException e) {
            return OrderResponse.ResponseCode.INTERNAL_SERVER_ERROR.getCode();
        }
    }
}
