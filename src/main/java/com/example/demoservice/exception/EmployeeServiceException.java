package com.example.demoservice.exception;

public class EmployeeServiceException extends RuntimeException{
    public EmployeeServiceException(String message) {
        super(message);
    }
}
