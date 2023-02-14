package com.example.demoservice.data;

import com.example.demoservice.dto.CreateEmployeeRequest;
import com.example.demoservice.model.Employee;

import java.time.LocalDate;

public class CreateEmployeeRequestDataBuilder {
    private long id = 1L;
    private String firstName = "zan";
    private String lastName = "pan";

    private String dateOfBirth = "1999-10-10";
    private String dateOfJoining = "2020-10-10";

    private long designationId = 1L;

    private CreateEmployeeRequestDataBuilder() {

    }
    public static CreateEmployeeRequestDataBuilder aCreateEmployeeRequest() {
        return new CreateEmployeeRequestDataBuilder();
    }

    public CreateEmployeeRequestDataBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateEmployeeRequestDataBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateEmployeeRequestDataBuilder withDateOfBirth (String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public CreateEmployeeRequestDataBuilder withDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
        return this;
    }

    public CreateEmployeeRequest build() {
        return CreateEmployeeRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .dateOfJoining(dateOfJoining)
                .designationId(id)
                .build();
    }

    public Employee employee() {
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(LocalDate.parse(dateOfBirth))
                .dateOfJoining(LocalDate.parse(dateOfJoining))
                .build();
    }

}
