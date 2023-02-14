package com.example.demoservice.controller;


import com.example.demoservice.dto.CreateEmployeeRequest;
import com.example.demoservice.dto.Response;
import com.example.demoservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employee", produces = "application/json")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createEmployee(@Valid  @RequestBody CreateEmployeeRequest request) {
        employeeService.createEmployee(request);
        return new ResponseEntity<>(Response.builder().message("Success").success(true).build(), HttpStatus.OK);

    }

}
