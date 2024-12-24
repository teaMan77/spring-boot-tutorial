package com.example.spring_boot_tutorial.controller;

import com.example.spring_boot_tutorial.entity.Department;
import com.example.spring_boot_tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
            .departmentName("IT")
            .departmentAddress("NY")
            .departmentCode("IT-01")
            .departmentId(1L)
            .build();
        }

    @Test
    void testSaveDepartment() throws Exception {

        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/departments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"departmentName\" : \"IT\",\r\n" + //
                        "\t\"departmentAddress\" : \"NY\",\r\n" + //
                        "\t\"departmentCode\" : \"IT-01\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDepartmentById() throws Exception {
        Long departmentId = 1L;

        when(departmentService.getDepartmentById(departmentId)).thenReturn(department);

        mockMvc.perform(get("/departments/{id}", departmentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()))
                .andExpect(jsonPath("$.departmentAddress").value(department.getDepartmentAddress()))
                .andExpect(jsonPath("$.departmentCode").value(department.getDepartmentCode()));
    }

}