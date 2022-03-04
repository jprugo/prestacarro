package com.gwtsas.prestacarro.controllers;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import com.gwtsas.prestacarro.exceptions.TokenRefreshException;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException exception,
            ServletWebRequest webRequest) throws IOException {
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    
    @ExceptionHandler(NoSuchElementException.class)
    public void handleConstraintViolationException(NoSuchElementException exception,
            ServletWebRequest webRequest) throws IOException {
        webRequest.getResponse().sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
    
    
    @ExceptionHandler(TokenRefreshException.class)
    public void handleConstraintViolationException(TokenRefreshException exception,
            ServletWebRequest webRequest) throws IOException {
        webRequest.getResponse().sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }
    
}    