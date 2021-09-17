package com.kosmo.funfunhaejwo.jpa.exception;

public class BadRequestLoginApiException extends RuntimeException {
    public BadRequestLoginApiException(String message) {
        super(message);
    }
}
