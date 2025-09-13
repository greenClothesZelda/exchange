package com.nhn.app.exchange.order.event;

//TODO 생산자 소비자패턴을 이용해서 threadpool을 만들기

import com.nhn.app.exchange.front.response.OrderResponse;
import com.nhn.app.exchange.order.dto.OrderDTO;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * ThreadPool을 생성 및 관리하는 클래스입니다.
 * ThreadPool은 queue를 이용하여 비동기적으로 작업을 처리할 수 있도록 합니다.
 * 작동방식은 과거 과제를 참고
 */

@Component
public class OrderEventConsumer {
    @Async
    @EventListener
    public void handleOrderEvent(OrderEvent orderEvent) {
        //TODO orderEvent를 처리하는 로직 작성 (서비스 호출)

        //아래는 test용 코드 작성시 지우거나 결과에 따라 변경할 것
        orderEvent.complete(OrderResponse.ResponseCode.SUCCESS);
    }
}
