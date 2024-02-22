package com.bug.reporting.system.bugreportingsystem.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFound(UserNotFoundException userNotFoundException) {
        Map<String, String> entryMap = new HashMap<>();
        entryMap.put("error message is", userNotFoundException.getMessage());
        return entryMap;
    }
}
