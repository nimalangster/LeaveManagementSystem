package com.sgic.hrm.leavesystem.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.hrm.leavesystem.entity.Department;
import com.sgic.hrm.leavesystem.service.DepartmentService;

@RestController

public class DepartmentController {
	
	@Autowired
	private DepartmentService DepartmentService;
	
	
	@PostMapping("/department")
	ResponseEntity<?> addDepartment(@RequestBody Department newDepartment) throws URISyntaxException {
		return DepartmentService.addDepartment(newDepartment);		
	}
	
	
	@GetMapping("/department/{id}")
	public Resource <Department> getDepartmentById(@PathVariable Integer id) {		
		return DepartmentService.getDepartmentById(id);		
	}	

	
	@GetMapping("/department")
	public Resources<Resource<Department>> getAllDepartments() {
		return DepartmentService.getAllDepartments();
	}
	
	
	@PutMapping("/department/{id}")
	ResponseEntity<?> editDepartment(@RequestBody Department newDepartment, @PathVariable Integer id) throws URISyntaxException {		
		return DepartmentService.editDepartment(newDepartment, id);
	}	

	
	@DeleteMapping("/department/{id}")
	ResponseEntity<Department> deleteDepartment(@PathVariable Integer id) {
		DepartmentService.deleteDepartment(id);		
		return ResponseEntity.noContent().build();
	}
		
}
