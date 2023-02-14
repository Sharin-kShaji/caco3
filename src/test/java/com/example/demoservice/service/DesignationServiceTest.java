package com.example.demoservice.service;

import com.example.demoservice.config.MapperConfig;
import com.example.demoservice.data.CreateDesignationRequestDataBuilder;
import com.example.demoservice.dto.CreateDesignationRequest;
import com.example.demoservice.exception.DesignationServiceException;
import com.example.demoservice.model.Designation;
import com.example.demoservice.repository.DesignationRepository;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DesignationServiceTest {


    @Mock
    private DesignationRepository mockDesignationRepository;

    @Spy
    private MapperConfig mockMapperConfig;
    private DesignationService designationService;



    @BeforeEach
    void init() {
        designationService = new DesignationService(mockDesignationRepository, mockMapperConfig);
    }

    @Test
    public void testCreateDesignation_Success() {
        CreateDesignationRequest request = CreateDesignationRequest.builder()
                .title("software developer")
                .build();

        designationService.createDesignation(request);
        Designation expected = Designation.builder()
                .title(request.getTitle())
                .build();

        ArgumentCaptor<Designation> designationArgumentCaptor =  ArgumentCaptor.forClass(Designation.class);
        verify(mockDesignationRepository).save(designationArgumentCaptor.capture());
        assertThat(designationArgumentCaptor.getValue(), allOf(HasPropertyWithValue.hasProperty("title", equalTo(expected.getTitle()))));
    }

    @Test
    public void createDesignation_Failure() {

        CreateDesignationRequest request = CreateDesignationRequestDataBuilder.aDesignationRequest().build();
//                CreateDesignationRequest.builder()
//                .title("software developer")
//                .build();
//        Designation designation = Designation.builder().title(request.getTitle()).build();
//        mockDesignationRepository.save(designation);
        when(mockDesignationRepository.countByTitleIgnoreCase(any())).thenReturn(2L);
        assertThrows(DesignationServiceException.class, () -> {
            designationService.createDesignation(request);
        });


    }


}
