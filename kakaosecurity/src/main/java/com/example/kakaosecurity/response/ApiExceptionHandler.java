package com.example.kakaosecurity.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ApiErrorResponse> RunTimeException(ApiErrorException ex) {
    	String errorCode = ex.getErrorEnum().getCode();
    	String errorMsg = ex.getErrorEnum().getMessage();
    	HttpStatus httpStatus = ex.getErrorEnum().getStatus();
        ApiErrorResponse response = new ApiErrorResponse(errorCode, errorMsg);
        return new ResponseEntity<>(response, httpStatus);
    }
}