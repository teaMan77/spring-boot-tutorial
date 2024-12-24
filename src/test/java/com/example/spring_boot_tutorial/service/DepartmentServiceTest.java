package com.example.spring_boot_tutorial.service;

import com.example.spring_boot_tutorial.entity.Department;
import com.example.spring_boot_tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockitoBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("NY")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        when(departmentRepository.findById(1L)).thenReturn(Optional.ofNullable(department));
    }

    @Test
    void saveDepartment() {
        Department requestDept  = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-06")
                .departmentAddress("NY")
                .build();
        Department savedDept = departmentService.saveDepartment(requestDept);
        assertEquals(1L, savedDept.getDepartmentId());
    }

    @Test
    void getDepartmentById() {
        Department department = departmentService.getDepartmentById(1L);
        assertEquals(1L, department.getDepartmentId());
    }
}