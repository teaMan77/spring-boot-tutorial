package com.example.spring_boot_tutorial.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.spring_boot_tutorial.entity.Department;
import com.example.spring_boot_tutorial.service.DepartmentService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

        public static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

        @Autowired
        private DepartmentService departmentService;

        @PostMapping("/")
        public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
            LOGGER.info("Inside the saveDepartment method....");
            return ResponseEntity.ok(departmentService.saveDepartment(department));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) {
            LOGGER.info("Inside the getDepartment method....");
            return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
        }

        @GetMapping("/")
        public ResponseEntity<List<Department>> getAllDepartments() {
            LOGGER.info("Inside the getAllDepartments method....");
            return ResponseEntity.ok(departmentService.getAllDepartments());
        }

        @PutMapping("/{id}")
        public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long departmentId, @Valid @RequestBody Department department) {
            LOGGER.info("Inside the updateDepartment method....");
            return ResponseEntity.ok(departmentService.updateDepartment(departmentId, department));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
            LOGGER.info("Inside the deleteDepartment method....");
            departmentService.deleteDepartment(departmentId);
            return ResponseEntity.ok("Department deleted successfully!");
        }

        @GetMapping("/name/{name}")
        public ResponseEntity<Department> getDepartmentByName(@PathVariable("name") String departmentName) {
            LOGGER.info("Inside the getDepartmentByName method....");
            return ResponseEntity.ok(departmentService.getDepartmentByName(departmentName));
        }

    }

