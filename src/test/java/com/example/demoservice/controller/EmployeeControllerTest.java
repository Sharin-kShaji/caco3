package com.example.demoservice.controller;

import com.example.demoservice.dto.CreateEmployeeRequest;
import com.example.demoservice.repository.EmployeeRepository;
import com.example.demoservice.service.EmployeeService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class EmployeeControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @AfterEach
    public void tear() {
        employeeRepository.deleteAll();
    }


    @Test
    @DisplayName("POST/ Create employee success")
    @SneakyThrows
    public void testCreateEmployee_Success() {
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .firstName("zan")
                .lastName("pam")
                .dateOfBirth("1999-10-10")
                .dateOfJoining("2022-10-10")
                .build();
        mockMvc.perform(
                post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.jsonToString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.is(true)));


    }
}
