package com.min32.blogforevery.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(S3Exception.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(S3Exception e){
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
}