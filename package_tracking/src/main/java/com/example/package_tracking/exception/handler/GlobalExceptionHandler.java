package com.example.package_tracking.exception.handler;

import com.example.package_tracking.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestControllerAdvice
public class    GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> content = new HashMap<>();
        content.put("message", ex.getMessage());
        content.put("timestamp", LocalDateTime.now());
        content.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(content, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        String errorMessage = ex.getMostSpecificCause().getMessage();
        log.error(errorMessage);

        if(errorMessage.contains("нарушает ограничение уникальности")) {
            error.put("message", extractUnique(errorMessage));
            log.error(extractUnique(errorMessage));
        }
        else if (errorMessage.contains("нарушает ограничение внешнего ключа")) {
            error.put("message", extractForeign(errorMessage));
        }
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    private String extractUnique(String message) {
        Pattern uniquePattern = Pattern.compile("\\(([\\w\\s,]+)\\)=\\(([^)]+)\\)");
        Matcher matcher = uniquePattern.matcher(message);

        if (matcher.find()) {
            String field = matcher.group(1);
            String value = matcher.group(2);
            return "Already exists " + field.toUpperCase() + " = " + value;
        }
        return message;
    }

    private String extractForeign(String message) {
        Pattern fkPattern = Pattern.compile("таблицы \"([^\"]+)\"");
        Matcher matcher = fkPattern.matcher(message);

        if (matcher.find()) {
            String table = matcher.group(1);
            return "Cannot delete: record is referenced in table " + table.toUpperCase();
        }
        return message;
    }
}
