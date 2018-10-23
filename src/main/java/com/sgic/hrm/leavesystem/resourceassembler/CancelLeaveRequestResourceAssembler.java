package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.CancelLeaveRequestController;
import com.sgic.hrm.leavesystem.entity.CancelLeaveRequest;

@Component

public class CancelLeaveRequestResourceAssembler implements ResourceAssembler<CancelLeaveRequest, Resource<CancelLeaveRequest>> {

	@Override
	public Resource<CancelLeaveRequest> toResource(CancelLeaveRequest cancelLeaveRequest) {

		return new Resource<>(cancelLeaveRequest,
			linkTo(methodOn(CancelLeaveRequestController.class).getCancelLeaveRequestById(cancelLeaveRequest.getId())).withSelfRel(),
			linkTo(methodOn(CancelLeaveRequestController.class).getAllCancelLeaveRequests()).withRel("CancelLeaveRequests"));
	}
}