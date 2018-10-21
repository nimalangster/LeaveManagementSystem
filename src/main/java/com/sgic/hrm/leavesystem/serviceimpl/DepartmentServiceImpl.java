package com.sgic.hrm.leavesystem.serviceimpl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sgic.hrm.leavesystem.controller.DepartmentController;
import com.sgic.hrm.leavesystem.entity.Department;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.DepartmentRepository;
import com.sgic.hrm.leavesystem.resourceassembler.DepartmentResourceAssembler;
import com.sgic.hrm.leavesystem.service.DepartmentService;




@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository DepartmentRepository;	
	@Autowired
	private DepartmentResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addDepartment(@RequestBody Department newDepartment) throws URISyntaxException {

		Resource<Department> resource = assembler.toResource(DepartmentRepository.save(newDepartment));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<Department>> getAllDepartments() {
		
		List<Resource<Department>> departments = DepartmentRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(departments,
			linkTo(methodOn(DepartmentController.class).getAllDepartments()).withSelfRel());
	}
	
	@Override	
	public Resource<Department> getDepartmentById(Integer id) {
		Department department = DepartmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Department.class.getSimpleName()));

		return assembler.toResource(department);
		
	}	 
	@Override
	public ResponseEntity<?> editDepartment(@RequestBody Department newDepartment, @PathVariable Integer id) throws URISyntaxException {

		Department department = DepartmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Department.class.getSimpleName()));		

		newDepartment.setId(id);
		Department updatedDepartment = DepartmentRepository.save(newDepartment);	
		Resource<Department> resource = assembler.toResource(updatedDepartment);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteDepartment(Integer id) {	
		Department department = DepartmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Department.class.getSimpleName()));
		DepartmentRepository.deleteById(id);		
	}

}
