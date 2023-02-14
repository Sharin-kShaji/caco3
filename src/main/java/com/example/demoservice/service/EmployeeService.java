package com.example.demoservice.service;


import com.example.demoservice.config.MapperConfig;
import com.example.demoservice.dto.CreateEmployeeRequest;
import com.example.demoservice.exception.DesignationServiceException;
import com.example.demoservice.exception.EmployeeServiceException;
import com.example.demoservice.model.Designation;
import com.example.demoservice.model.Employee;
import com.example.demoservice.repository.DesignationRepository;
import com.example.demoservice.repository.EmployeeRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DesignationRepository designationRepository;
    private final MapperConfig mapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, MapperConfig mapper, DesignationRepository designationRepository) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.designationRepository = designationRepository;

    }


    @SneakyThrows
    @Transactional
    public void createEmployee(CreateEmployeeRequest request) {
        Optional<Designation> designation = designationRepository.findById(request.getDesignationId());
        if (designation.isEmpty()) {
            throw new DesignationServiceException("Designation not found");
        }
        LocalDate dob = LocalDate.parse(request.getDateOfBirth(), DateTimeFormatter.ofPattern("yyy-MM-dd"));
        LocalDate currentDate = LocalDate.now();
        if(dob.isAfter(LocalDate.now())) {
            log.error("Date of birth cannot be future date");
            throw new EmployeeServiceException("Date of birth cannot be future date");
        }
//        Period.between(currentDate, dob).getYears()
        if((currentDate.getYear() - dob.getYear()) <  17) {
            log.error("Age should 18 0r above");
            throw new EmployeeServiceException("Age should 18 0r above");
        }

        log.info("Creating Employee");
        Employee employee = mapper.getMapper().map(request, Employee.class);
        employee.setDesignation(designation.get());
        employeeRepository.save(employee);
        log.info("employee with id {} created successfully", employee.getId());
    }

}
