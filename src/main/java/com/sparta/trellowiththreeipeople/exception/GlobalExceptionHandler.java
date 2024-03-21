package com.sparta.trellowiththreeipeople.exception;

import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException e) {

        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullPointerException(NullPointerException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)

    public ResponseEntity<String> UserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> BoardNotFoundException(BoardNotFoundException e) {

        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequestException(BadRequestException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(BoardUserNotFoundException.class)
    public ResponseEntity<String> BoardUserNotFoundException(BoardUserNotFoundException e) {

        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(BoardUserExistException.class)
    public ResponseEntity<String> BoardUserExistException(BoardUserExistException e) {

        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> ValidationException(ValidationException e) {

        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
