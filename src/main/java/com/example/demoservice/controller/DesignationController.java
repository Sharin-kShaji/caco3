package com.example.demoservice.controller;


import com.example.demoservice.dto.CreateDesignationRequest;
import com.example.demoservice.dto.Response;
import com.example.demoservice.service.DesignationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/designation", produces = "application/json")
public class DesignationController {

    private DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;

    }

    @PostMapping("/create")
    public ResponseEntity<Response> createDesignation(@Valid @RequestBody CreateDesignationRequest request) {
        designationService.createDesignation(request);
        return new ResponseEntity<>(Response.builder().success(true).message("Success").build(), HttpStatus.OK);
    }
}
