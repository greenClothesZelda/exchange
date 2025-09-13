package com.nhn.app.exchange.order.exception;

public class FailDBControll extends RuntimeException {
    public FailDBControll(String message) {
        super(message);
    }
}
