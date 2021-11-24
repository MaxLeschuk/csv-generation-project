package com.epam.training.csvgeneration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({TechnicalException.class})
    public ResponseEntity<Object> technicalError(Exception ex,
                                                 WebRequest request) {
        ApiError apiError = new ApiError("technical-error", HttpStatus.SERVICE_UNAVAILABLE,"Please try again later.",ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}