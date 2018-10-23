package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.CarryForwardRejectReasonController;
import com.sgic.hrm.leavesystem.entity.CarryForwardRejectReason;

@Component

public class CarryForwardRejectReasonResourceAssembler implements ResourceAssembler<CarryForwardRejectReason, Resource<CarryForwardRejectReason>> {

	@Override
	public Resource<CarryForwardRejectReason> toResource(CarryForwardRejectReason carryForwardRejectReason) {

		return new Resource<>(carryForwardRejectReason,
			linkTo(methodOn(CarryForwardRejectReasonController.class).getCarryForwardRejectReasonById(carryForwardRejectReason.getId())).withSelfRel(),
			linkTo(methodOn(CarryForwardRejectReasonController.class).getAllCarryForwardRejectReasons()).withRel("CarryForwardRejectReasons"));
	}
}