package com.sgic.hrm.leavesystem.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.hrm.leavesystem.entity.CarryForward;
import com.sgic.hrm.leavesystem.service.CarryForwardService;

@RestController

public class CarryForwardController {
	
	@Autowired
	private CarryForwardService carryForwardService;
	
	
	@PostMapping("/carryforward")
	ResponseEntity<?> addCarryForward(@RequestBody CarryForward newCarryForward) throws URISyntaxException {
		return carryForwardService.addCarryForward(newCarryForward);		
	}
	
	
	@GetMapping("/carryforward/{id}")
	public Resource <CarryForward> getCarryForwardById(@PathVariable Integer id) {		
		return carryForwardService.getCarryForwardById(id);		
	}	

	
	@GetMapping("/carryforward")
	public Resources<Resource<CarryForward>> getAllCarryForwards() {
		return carryForwardService.getAllCarryForwards();
	}
	
	
	@PutMapping("/carryforward/{id}")
	ResponseEntity<?> editCarryForward(@RequestBody CarryForward newCarryForward, @PathVariable Integer id) throws URISyntaxException {		
		return carryForwardService.editCarryForward(newCarryForward, id);
	}	

	
	@DeleteMapping("/carryforward/{id}")
	ResponseEntity<CarryForward> deleteCarryForward(@PathVariable Integer id) {
		carryForwardService.deleteCarryForward(id);		
		return ResponseEntity.noContent().build();
	}
		
}
