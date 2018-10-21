package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.LeaveTypeController;
import com.sgic.hrm.leavesystem.entity.LeaveType;

@Component

public class LeaveTypeResourceAssembler implements ResourceAssembler<LeaveType, Resource<LeaveType>> {

	@Override
	public Resource<LeaveType> toResource(LeaveType leaveType) {

		return new Resource<>(leaveType,
			linkTo(methodOn(LeaveTypeController.class).getLeaveTypeById(leaveType.getId())).withSelfRel(),
			linkTo(methodOn(LeaveTypeController.class).getAllLeaveTypes()).withRel("leavetypes"));
	}
}