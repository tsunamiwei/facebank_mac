package com.pafacebank.facebank.exception;

public class AuthenticationException extends  RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
