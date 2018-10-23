package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.CarryForwardController;
import com.sgic.hrm.leavesystem.entity.CarryForward;

@Component

public class CarryForwardResourceAssembler implements ResourceAssembler<CarryForward, Resource<CarryForward>> {

	@Override
	public Resource<CarryForward> toResource(CarryForward carryForward) {

		return new Resource<>(carryForward,
			linkTo(methodOn(CarryForwardController.class).getCarryForwardById(carryForward.getId())).withSelfRel(),
			linkTo(methodOn(CarryForwardController.class).getAllCarryForwards()).withRel("CarryForwards"));
	}
}