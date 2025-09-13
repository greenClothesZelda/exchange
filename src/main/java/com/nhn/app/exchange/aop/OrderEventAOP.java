package com.nhn.app.exchange.aop;

import com.nhn.app.exchange.order.dto.OrderDTO;
import com.nhn.app.exchange.order.dto.OrderDeleteDTO;
import com.nhn.app.exchange.order.event.put.OrderEvent;
import com.nhn.app.exchange.order.event.delete.OrderDeleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class OrderEventAOP {
    @Pointcut("execution(public * com.nhn.app.exchange.order.event.put.OrderEventConsumer+.handleOrderEvent(..))")
    public void orderEventLog(){

    }

    @Before("orderEventLog()")
    public void beforeOrderEventLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        OrderEvent orderEvent = (OrderEvent) args[0];
        OrderDTO orderDTO = orderEvent.getOrderDTO();
        log.debug("Order Event: {}" , orderDTO.toString());
    }

    @Pointcut("execution(public * com.nhn.app.exchange.order.event.delete.OrderDeleteEventConsumer+.handleOrderDeleteEvent(..))")
    public void orderDeleteEventLog(){

    }

    @Before("orderDeleteEventLog()")
    public void beforeOrderDeleteEventLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        OrderDeleteEvent orderDeleteEvent = (OrderDeleteEvent) args[0];
        OrderDeleteDTO orderDeleteDTO = orderDeleteEvent.getOrderDeleteDTO();
        log.debug("Order Delete Event: {}" , orderDeleteDTO.toString());
    }
}
