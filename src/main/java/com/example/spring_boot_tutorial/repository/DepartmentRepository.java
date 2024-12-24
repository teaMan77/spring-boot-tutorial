package com.example.spring_boot_tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_boot_tutorial.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);

}
