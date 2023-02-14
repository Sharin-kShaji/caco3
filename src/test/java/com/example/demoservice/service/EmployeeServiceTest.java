package com.example.demoservice.service;

import com.example.demoservice.config.MapperConfig;
import com.example.demoservice.data.CreateDesignationRequestDataBuilder;
import com.example.demoservice.data.CreateEmployeeRequestDataBuilder;
import com.example.demoservice.dto.CreateDesignationRequest;
import com.example.demoservice.dto.CreateEmployeeRequest;
import com.example.demoservice.exception.EmployeeServiceException;
import com.example.demoservice.model.Designation;
import com.example.demoservice.model.Employee;
import com.example.demoservice.repository.DesignationRepository;
import com.example.demoservice.repository.EmployeeRepository;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private  EmployeeRepository mockEmployeeRepository;
   @Mock
    private DesignationRepository mockDesignationRepository;
    @Spy
    private  MapperConfig mockMapper;
    private EmployeeService employeeService;

    @BeforeEach
    void init() {
        employeeService = new EmployeeService(mockEmployeeRepository, mockMapper, mockDesignationRepository);
    }

//    @Test
//    public void testCreateEmployee_Success() {
//        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
//                .firstName("zan")
//                .lastName("pam")
//                .dateOfBirth("1999-10-10")
//                .dateOfJoining("2022-10-10")
//                .build();
//        // when
//        employeeService.createEmployee(request);
//
//        //Then
//        Employee expected = Employee.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
//                .dateOfJoining(LocalDate.parse(request.getDateOfJoining()))
//                .build();
//
//        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
//        verify(mockEmployeeRepository).save(employeeArgumentCaptor.capture());
//        assertThat(employeeArgumentCaptor.getValue(), allOf(
//                HasPropertyWithValue.hasProperty("firstName", equalTo(expected.getFirstName())),
//                HasPropertyWithValue.hasProperty("lastName", equalTo(expected.getLastName())),
//                HasPropertyWithValue.hasProperty("dateOfBirth", equalTo(expected.getDateOfBirth())),
//                HasPropertyWithValue.hasProperty("dateOfJoining", equalTo(expected.getDateOfJoining()))
//        ));
//
//
//
//
//    }

    @Test
    @DisplayName("test create employee Success")
    public void testEmployeeService_success() {


        CreateEmployeeRequest request = CreateEmployeeRequestDataBuilder.aCreateEmployeeRequest().build();
        Designation designation = CreateDesignationRequestDataBuilder.aDesignationRequest().designation();
        when(mockDesignationRepository.findById(any())).thenReturn(Optional.of(designation));
//                CreateEmployeeRequest.builder()
//                .firstName("zan")
//                .lastName("pam")
//                .dateOfBirth("1999-10-10")
//                .dateOfJoining("2022-10-10")
//                .build();


        employeeService.createEmployee(request);
        Employee expected = CreateEmployeeRequestDataBuilder.aCreateEmployeeRequest().employee();
//                Employee.builder()
//                .lastName(request.getLastName())
//                .firstName(request.getFirstName())
//                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
//                .dateOfJoining(LocalDate.parse(request.getDateOfJoining()))
//                .build();
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(mockEmployeeRepository).save(employeeArgumentCaptor.capture());
        assertThat(employeeArgumentCaptor.getValue(),
                allOf(
                        HasPropertyWithValue.hasProperty("firstName", equalTo(expected.getFirstName())),
                        HasPropertyWithValue.hasProperty("lastName", equalTo(expected.getLastName())),
                        HasPropertyWithValue.hasProperty("dateOfBirth", equalTo(expected.getDateOfBirth())),
                        HasPropertyWithValue.hasProperty("dateOfJoining", equalTo(expected.getDateOfJoining())),
                        allOf(
                        HasPropertyWithValue.hasProperty("designation", hasProperty("title", equalTo(designation.getTitle()))))
                        ));
    }


    @Test
    @DisplayName("test create employee failure, dob cannot be future date")
    public void testCreateEmployee_Failure() {
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .firstName("zan")
                .lastName("pam")
                .dateOfBirth("2025-10-10")
                .dateOfJoining("2022-10-10")
                .build();

        assertThrows(EmployeeServiceException.class, () -> {
            employeeService.createEmployee(request);
        });


    }

    @Test
    @DisplayName("test create employee failure, age should be 18 and above")
    public void testCreateEmployee_failure() {

        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .firstName("zan")
                .lastName("pam")
                .dateOfBirth("2020-10-10")
                .dateOfJoining("2022-10-10")
                .build();
        assertThrows(EmployeeServiceException.class, () -> {
            employeeService.createEmployee(request);
        });

    }




}
