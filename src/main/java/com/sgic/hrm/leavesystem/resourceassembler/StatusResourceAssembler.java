package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.StatusController;
import com.sgic.hrm.leavesystem.entity.Status;

@Component

public class StatusResourceAssembler implements ResourceAssembler<Status, Resource<Status>> {

	@Override
	public Resource<Status> toResource(Status status) {

		return new Resource<>(status,
			linkTo(methodOn(StatusController.class).getStatusById(status.getId())).withSelfRel(),
			linkTo(methodOn(StatusController.class).getAllStatuses()).withRel("Statuses"));
	}
}