package com.nhn.app.exchange.order.event.delete;

import com.nhn.app.exchange.front.response.OrderCancelResponse;
import com.nhn.app.exchange.front.response.OrderResponse;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderDeleteEventConsumer {
    @Async
    @EventListener
    public void handleOrderDeleteEvent(OrderDeleteEvent orderDeleteEvent) {
        //TODO orderDeleteEvent를 처리하는 로직 작성

        //아래는 test용 코드 작성시 지우거나 결과에 따라 변경할 것
        orderDeleteEvent.complete(OrderCancelResponse.ResponseCode.SUCCESS);
    }
}
