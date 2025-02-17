package com.project.minor1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Object> handler(TxnException txnException) {
        return new ResponseEntity<>(txnException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
