package com.example.desafio_pic_pay.exceptions;

import com.example.desafio_pic_pay.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> handleDuplicateException(DataIntegrityViolationException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("User already exists!", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleEntityNotFound(EntityNotFoundException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("User not found.", "404");
        return ResponseEntity.status(404).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGeneralException(Exception e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), "500");
        return ResponseEntity.status(500).body(exceptionDTO);
    }

}
