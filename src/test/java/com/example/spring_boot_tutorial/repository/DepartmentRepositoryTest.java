package com.example.spring_boot_tutorial.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring_boot_tutorial.entity.Department;

@DataJpaTest
@ActiveProfiles("test")
public class DepartmentRepositoryTest {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
            .departmentName("EE")
            .departmentCode("EE-09")
            .departmentAddress("KA")
            .build();

            entityManager.persistAndFlush(department);
    }

    @Test
    void testFindByDepartmentNameIgnoreCase() {
        Department deptDB = departmentRepository.findByDepartmentNameIgnoreCase("EE").get();
        assertEquals("EE", deptDB.getDepartmentName());
    }
}
