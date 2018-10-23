package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.LoginController;
import com.sgic.hrm.leavesystem.entity.Login;

@Component

public class LoginResourceAssembler implements ResourceAssembler<Login, Resource<Login>> {

	@Override
	public Resource<Login> toResource(Login login) {

		return new Resource<>(login,
			linkTo(methodOn(LoginController.class).getLoginById(login.getId())).withSelfRel(),
			linkTo(methodOn(LoginController.class).getAllLogins()).withRel("Logins"));
	}
}