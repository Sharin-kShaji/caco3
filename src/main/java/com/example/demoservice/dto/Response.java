package com.example.demoservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Response {

    private String message;
    private boolean success;
}
