package com.example.demoservice.data;

import com.example.demoservice.dto.CreateDesignationRequest;
import com.example.demoservice.model.Designation;

public class CreateDesignationRequestDataBuilder {

    private long id = 1L;
    private String title = "software developer";

    private String description = "Software developer";

    private CreateDesignationRequestDataBuilder() {

    }

    public static CreateDesignationRequestDataBuilder aDesignationRequest() {
        return new CreateDesignationRequestDataBuilder();
    }

    public CreateDesignationRequestDataBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateDesignationRequestDataBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateDesignationRequest build() {
        return CreateDesignationRequest.builder()
                .title(title)
                .description(description)
                .build();
    }

    public Designation designation() {
        return Designation
                .builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }
}
