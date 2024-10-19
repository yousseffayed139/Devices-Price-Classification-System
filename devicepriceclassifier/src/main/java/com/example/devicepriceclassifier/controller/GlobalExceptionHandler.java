package com.example.devicepriceclassifier.controller;

import com.example.devicepriceclassifier.exception.DeviceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeviceAlreadyExistsException.class)
    public ResponseEntity<String> handleDeviceAlreadyExistsException(DeviceAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
