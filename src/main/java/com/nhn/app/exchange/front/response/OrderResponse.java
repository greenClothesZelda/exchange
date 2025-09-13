package com.nhn.app.exchange.front.response;

public class OrderResponse {
    public enum ResponseCode {
        SUCCESS(200),
        NOT_DEFINED_IN_ORDER_BOOK(404),
        VIOLATION_OF_TRADING_RULES(416),
        INTERNAL_SERVER_ERROR(500);

        private final int code;
        private final String message;
        ResponseCode(int code) {
            this.code = code;
            this.message = "";
        }
        public int getCode() {
            return code;
        }
    }
}
