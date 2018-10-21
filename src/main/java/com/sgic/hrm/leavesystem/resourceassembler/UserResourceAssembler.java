package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.UserController;
import com.sgic.hrm.leavesystem.entity.User;

@Component

public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

	@Override
	public Resource<User> toResource(User user) {

		return new Resource<>(user,
			linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
			linkTo(methodOn(UserController.class).getAllUsers()).withRel("Users"));
	}
}