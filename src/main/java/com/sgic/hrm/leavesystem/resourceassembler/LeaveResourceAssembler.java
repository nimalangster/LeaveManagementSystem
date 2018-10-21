package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.LeaveController;
import com.sgic.hrm.leavesystem.entity.Leave;

@Component

public class LeaveResourceAssembler implements ResourceAssembler<Leave, Resource<Leave>> {

	@Override
	public Resource<Leave> toResource(Leave leave) {

		return new Resource<>(leave,
			linkTo(methodOn(LeaveController.class).getLeaveById(leave.getId())).withSelfRel(),
			linkTo(methodOn(LeaveController.class).getAllLeaves()).withRel("leaves"));
	}
}