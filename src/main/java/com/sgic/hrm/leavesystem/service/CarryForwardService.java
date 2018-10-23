package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.CarryForward;

public interface CarryForwardService {
	
	ResponseEntity<?> addCarryForward(CarryForward CarryForward) throws URISyntaxException;
	Resources<Resource<CarryForward>>  getAllCarryForwards();
	Resource <CarryForward> getCarryForwardById(Integer id);
	void deleteCarryForward(Integer id);
	ResponseEntity<?> editCarryForward(CarryForward carryForward, Integer id) throws URISyntaxException;

}
