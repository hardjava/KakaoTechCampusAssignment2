package com.kakaotechcampus.schedule_app.Lv3_6;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {

    // Validation Failed Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    // Email Duplication Exception
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicationError(DataIntegrityViolationException e){

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "Already Registered Email"));
    }

    // Field Not Allowed Exception
    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<Map<String, String>> handleUnknownFieldError(UnrecognizedPropertyException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Contains field that is not allowed"));
    }

    // Response Status Exception
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusError(ResponseStatusException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getReason());

        return ResponseEntity.status(e.getStatusCode()).body(errors);
    }
}
