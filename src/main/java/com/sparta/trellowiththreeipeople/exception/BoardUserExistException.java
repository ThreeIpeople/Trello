package com.sparta.trellowiththreeipeople.exception;

public class BoardUserExistException extends RuntimeException {
    public BoardUserExistException(String message) {
        super(message);
    }
}
