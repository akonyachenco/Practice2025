package com.example.package_tracking.exception.handler;

import com.example.package_tracking.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class EntityNotFoundExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> content = new HashMap<>();
        content.put("message", ex.getMessage());
        content.put("timestamp", LocalDateTime.now());
        content.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(content, HttpStatus.NOT_FOUND);
    }
}
