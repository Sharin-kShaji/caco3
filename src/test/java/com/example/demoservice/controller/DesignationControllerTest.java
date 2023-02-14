package com.example.demoservice.controller;


import com.example.demoservice.data.CreateDesignationRequestDataBuilder;
import com.example.demoservice.dto.CreateDesignationRequest;
import com.example.demoservice.repository.DesignationRepository;
import com.example.demoservice.util.TestUtil;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DesignationControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;


    @Autowired
    private DesignationRepository designationRepository;

    @BeforeEach
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @AfterEach
    public void tear() {
        designationRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    @DisplayName("POST / Create designation success")
    public void createDesignation_Success() {
        CreateDesignationRequest createDesignationRequest = CreateDesignationRequestDataBuilder.aDesignationRequest().build();
//                CreateDesignationRequest.builder()
//                .title("software developer")
//                .build();

        mockMvc.perform(
                post("/designation/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.jsonToString(createDesignationRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", Matchers.is(true)));

    }
}
