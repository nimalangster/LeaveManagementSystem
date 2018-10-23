package com.sgic.hrm.leavesystem.resourceassembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.sgic.hrm.leavesystem.controller.HolidayController;
import com.sgic.hrm.leavesystem.entity.Holiday;

@Component

public class HolidayResourceAssembler implements ResourceAssembler<Holiday, Resource<Holiday>> {

	@Override
	public Resource<Holiday> toResource(Holiday holiday) {

		return new Resource<>(holiday,
			linkTo(methodOn(HolidayController.class).getHolidayById(holiday.getId())).withSelfRel(),
			linkTo(methodOn(HolidayController.class).getAllHolidays()).withRel("Holidays"));
	}
}