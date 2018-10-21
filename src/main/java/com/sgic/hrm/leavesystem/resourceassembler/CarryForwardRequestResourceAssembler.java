package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.CarryForwardRequestController;
import com.sgic.hrm.leavesystem.entity.CarryForwardRequest;

@Component

public class CarryForwardRequestResourceAssembler implements ResourceAssembler<CarryForwardRequest, Resource<CarryForwardRequest>> {

	@Override
	public Resource<CarryForwardRequest> toResource(CarryForwardRequest carryForwardRequest) {

		return new Resource<>(carryForwardRequest,
			linkTo(methodOn(CarryForwardRequestController.class).getCarryForwardRequestById(carryForwardRequest.getId())).withSelfRel(),
			linkTo(methodOn(CarryForwardRequestController.class).getAllCarryForwardRequests()).withRel("CarryForwardRequests"));
	}
}