package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.RejectLeaveRequestController;
import com.sgic.hrm.leavesystem.entity.RejectLeaveRequest;

@Component

public class RejectLeaveRequestResourceAssembler implements ResourceAssembler<RejectLeaveRequest, Resource<RejectLeaveRequest>> {

	@Override
	public Resource<RejectLeaveRequest> toResource(RejectLeaveRequest rejectLeaveRequest) {

		return new Resource<>(rejectLeaveRequest,
			linkTo(methodOn(RejectLeaveRequestController.class).getRejectLeaveRequestById(rejectLeaveRequest.getId())).withSelfRel(),
			linkTo(methodOn(RejectLeaveRequestController.class).getAllRejectLeaveRequests()).withRel("RejectLeaveRequests"));
	}
}