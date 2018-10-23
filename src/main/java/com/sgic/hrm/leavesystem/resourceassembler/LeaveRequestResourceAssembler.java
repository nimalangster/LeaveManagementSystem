package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.LeaveRequestController;
import com.sgic.hrm.leavesystem.entity.LeaveRequest;

@Component

public class LeaveRequestResourceAssembler implements ResourceAssembler<LeaveRequest, Resource<LeaveRequest>> {

	@Override
	public Resource<LeaveRequest> toResource(LeaveRequest leaveRequest) {

		return new Resource<>(leaveRequest,
			linkTo(methodOn(LeaveRequestController.class).getLeaveRequestById(leaveRequest.getId())).withSelfRel(),
			linkTo(methodOn(LeaveRequestController.class).getAllLeaveRequests()).withRel("LeaveRequests"));
	}
}