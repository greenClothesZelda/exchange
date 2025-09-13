package com.nhn.app.exchange.front.response;

public class OrderCancelResponse {
    public enum ResponseCode {
        SUCCESS(200),
        NOT_DEFINED_IN_ORDER_BOOK(404),
        INTERNAL_SERVER_ERROR(500);

        private final int code;
        ResponseCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }
}
