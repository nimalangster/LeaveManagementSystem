package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.RoleController;
import com.sgic.hrm.leavesystem.entity.Role;

@Component

public class RoleResourceAssembler implements ResourceAssembler<Role, Resource<Role>> {

	@Override
	public Resource<Role> toResource(Role role) {

		return new Resource<>(role,
			linkTo(methodOn(RoleController.class).getRoleById(role.getId())).withSelfRel(),
			linkTo(methodOn(RoleController.class).getAllRoles()).withRel("roles"));
	}
}