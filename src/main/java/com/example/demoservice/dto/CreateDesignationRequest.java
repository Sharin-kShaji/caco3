package com.example.demoservice.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = CreateDesignationRequest.CreateDesignationRequestBuilder.class)
public class CreateDesignationRequest {

    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateDesignationRequestBuilder {}

    @ApiModelProperty(notes = "Designation is mandatory and unique", required = true)
    private String title;

    @ApiModelProperty(notes = "Description about designation")
    private String description;
}
