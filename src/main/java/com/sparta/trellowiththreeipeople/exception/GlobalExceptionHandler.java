package com.sparta.trellowiththreeipeople.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "예외 핸들링")
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String INTERNAL_ERROR_500 = "서버 내부 오류가 발생했습니다. / Please Contact Admin";

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleAllUncaughtException(Exception ex) {
        log.error(ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(INTERNAL_ERROR_500);
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<String> handleApiException(ApiException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(INTERNAL_ERROR_500);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String message = "지원하지 않는 메서드입니다. Expected: " + ex.getSupportedHttpMethods() + " Actual : " + ex.getMethod();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullPointerException(NullPointerException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> UserNotFoundException(ApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(ExceptionStatus.NOT_FOUND_USER.getStatusCode())
                .body(ExceptionStatus.NOT_FOUND_USER.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> BoardNotFoundException(ApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(ExceptionStatus.NOT_FOUND_BOARD.getStatusCode())
                .body(ExceptionStatus.NOT_FOUND_BOARD.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequestException(BadRequestException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> BoardUserNotFoundException(ApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(ExceptionStatus.NOT_FOUND_BOARD_USER.getStatusCode())
                .body(ExceptionStatus.NOT_FOUND_BOARD_USER.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> BoardUserExistException(ApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(ExceptionStatus.EXIST_BoardUser.getStatusCode())
                .body(ExceptionStatus.EXIST_BoardUser.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> AuthNotExistException(ApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(ExceptionStatus.NOT_EXIST_AUTH.getStatusCode())
                .body(ExceptionStatus.NOT_EXIST_AUTH.getMessage());
    }

}
