package com.example.spring_boot_tutorial.service;


import java.util.List;

import com.example.spring_boot_tutorial.entity.Department;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public Department getDepartmentById(Long departmentId);

    public List<Department> getAllDepartments();

    public Department updateDepartment(Long departmentId, Department department);

    public void deleteDepartment(Long departmentId);

	public Department getDepartmentByName(String departmentName);

}
