package com.example.demoservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonDeserialize(builder = CreateEmployeeRequest.CreateEmployeeRequestBuilder.class)
public class CreateEmployeeRequest {

    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateEmployeeRequestBuilder {}

    @ApiModelProperty(notes = "First name is mandatory", required = true)
    private String firstName;


    @ApiModelProperty(notes = "Middle name of an employee")
    private String middleName;

    @ApiModelProperty(notes = "Last name is mandatory", required = true)
    private String lastName;



    @JsonFormat(pattern = "yyy-MM-dd")
    @ApiModelProperty(notes = "date of birth of employee", required = true)
    private String dateOfBirth;



    @JsonFormat(pattern = "yyy-MM-dd")
    @ApiModelProperty(notes = "joining date of employee", required = true)
    private String dateOfJoining;


    @ApiModelProperty(notes = "designation id is mandatory")
    private long designationId;

}
