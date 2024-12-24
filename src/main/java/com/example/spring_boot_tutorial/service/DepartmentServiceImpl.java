package com.example.spring_boot_tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import com.example.spring_boot_tutorial.entity.Department;
import com.example.spring_boot_tutorial.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();

        if (departments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No department exists!");
        }
		return departments;
	}

	@Override
	public Department updateDepartment(Long id, Department department) {
		Department existingDept = departmentRepository.findById(id)
		.orElseThrow(() -> 
					new ResponseStatusException(HttpStatus.NOT_FOUND, "Department does not exist!"));
		existingDept.setDepartmentAddress(department.getDepartmentAddress());
		existingDept.setDepartmentCode(department.getDepartmentCode());
		existingDept.setDepartmentName(department.getDepartmentName());

		return departmentRepository.save(existingDept);
	}

	@Override
	public Department saveDepartment(Department department) {
		if(department == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department cannot be null");
		
		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> 
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with given id not found"));
		return department;
	}

	@Override
	public void deleteDepartment(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> 
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with the given id doesn't exist"));
		departmentRepository.delete(department);
	}

	@Override
	public Department getDepartmentByName(String departmentName) {
		Department department = departmentRepository.findByDepartmentNameIgnoreCase(departmentName).orElseThrow(() -> 
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with the given name not found"));
		return department;
	}
}
