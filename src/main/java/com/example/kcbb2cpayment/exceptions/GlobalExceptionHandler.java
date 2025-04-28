package com.example.kcbb2cpayment.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ErrorDetails> handlePaymentFailedException(PaymentFailedException e) {
        return new ResponseEntity<>(getErrorDetails(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorDetails> handleInternalServerErrorException(HttpServerErrorException e) {
        return new ResponseEntity<>(getErrorDetails(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDetails getErrorDetails(Exception e) {
        return ErrorDetails.builder()
                .details(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();
    }

}
