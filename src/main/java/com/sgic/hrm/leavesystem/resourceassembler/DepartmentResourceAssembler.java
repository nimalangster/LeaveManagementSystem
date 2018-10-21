package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.DepartmentController;
import com.sgic.hrm.leavesystem.controller.RoleController;
import com.sgic.hrm.leavesystem.entity.Department;
import com.sgic.hrm.leavesystem.entity.Role;

@Component

public class DepartmentResourceAssembler implements ResourceAssembler<Department, Resource<Department>> {

	@Override
	public Resource<Department> toResource(Department department) {

		return new Resource<>(department,
			linkTo(methodOn(DepartmentController.class).getDepartmentById(department.getId())).withSelfRel(),
			linkTo(methodOn(DepartmentController.class).getAllDepartments()).withRel("Departments"));
	}
}