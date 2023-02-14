package com.example.demoservice.exception;


import com.example.demoservice.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmployeeServiceException.class, DesignationServiceException.class})
    public ResponseEntity<Object> exceptions(Exception ex) {
        return new ResponseEntity<>(Response.builder().message(ex.getMessage()).success(false).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class, SQLException.class})
    public ResponseEntity<Object> validationException(Exception ex) {
        return new ResponseEntity<>(Response.builder().message("Validation Error").success(false).build(), HttpStatus.BAD_REQUEST);
    }
}
