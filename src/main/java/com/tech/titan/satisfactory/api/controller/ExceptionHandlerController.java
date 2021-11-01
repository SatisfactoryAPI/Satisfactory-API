package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.exception.contract.NotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Constraint Violation: Resource Already Exists.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleConflict(Exception e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
