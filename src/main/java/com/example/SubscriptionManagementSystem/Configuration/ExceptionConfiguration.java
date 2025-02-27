package com.example.SubscriptionManagementSystem.Configuration;

import com.example.SubscriptionManagementSystem.Exception.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionConfiguration {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityViolationException(SQLIntegrityConstraintViolationException ex){
        if(ex.getMessage().contains("Duplicate Entry")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Key Already Exists");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DB Error");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwt(ExpiredJwtException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("JWT Token Expired");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(NoHandlerFoundException ex){

        Map<String,Object> errorDetails = new HashMap<>();

        errorDetails.put("status", HttpStatus.NOT_FOUND.value());
        errorDetails.put("error", "Not Found");
        errorDetails.put("message", "The requested resource was not found");
        errorDetails.put("path", ex.getRequestURL());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException ex){
        Map<String,Object> errorDetails = new HashMap<>();
        errorDetails.put("status",HttpStatus.NOT_FOUND);
        errorDetails.put("message",ex.getMessage());
        errorDetails.put("error", "Resource not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

    }

}
