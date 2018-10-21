package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.Department;
public interface DepartmentService {
	
	ResponseEntity<?> addDepartment(Department department) throws URISyntaxException;
	Resources<Resource<Department>>  getAllDepartments();
	Resource <Department> getDepartmentById(Integer id);
	void deleteDepartment(Integer id);
	ResponseEntity<?> editDepartment(Department department, Integer id) throws URISyntaxException;
}