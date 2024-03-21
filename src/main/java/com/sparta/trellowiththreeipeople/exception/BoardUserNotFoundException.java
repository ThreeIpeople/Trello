package com.sparta.trellowiththreeipeople.exception;

public class BoardUserNotFoundException extends RuntimeException {
    public BoardUserNotFoundException(String message) {
        super(message);
    }
}
