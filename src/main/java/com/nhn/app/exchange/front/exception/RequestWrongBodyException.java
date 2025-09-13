package com.nhn.app.exchange.front.exception;

public class RequestWrongBodyException extends RuntimeException {
    public RequestWrongBodyException(String message) {
        super(message);
    }
    public RequestWrongBodyException(){
        super("Request Body is Wrong");
    }
}
