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

import com.sgic.hrm.leavesystem.entity.CarryForwardRejectReason;
import com.sgic.hrm.leavesystem.service.CarryForwardRejectReasonService;

@RestController

public class CarryForwardRejectReasonController {
	
	@Autowired
	private CarryForwardRejectReasonService carryForwardRejectReasonService;
	
	
	@PostMapping("/carryforwardrejectreason")
	ResponseEntity<?> addCarryForwardRejectReason(@RequestBody CarryForwardRejectReason newCarryForwardRejectReason) throws URISyntaxException {
		return carryForwardRejectReasonService.addCarryForwardRejectReason(newCarryForwardRejectReason);		
	}
	
	
	@GetMapping("/carryforwardrejectreason/{id}")
	public Resource <CarryForwardRejectReason> getCarryForwardRejectReasonById(@PathVariable Integer id) {		
		return carryForwardRejectReasonService.getCarryForwardRejectReasonById(id);		
	}	

	
	@GetMapping("/carryforwardrejectreason")
	public Resources<Resource<CarryForwardRejectReason>> getAllCarryForwardRejectReasons() {
		return carryForwardRejectReasonService.getAllCarryForwardRejectReasons();
	}
	
	
	@PutMapping("/carryforwardrejectreason/{id}")
	ResponseEntity<?> editCarryForwardRejectReason(@RequestBody CarryForwardRejectReason newCarryForwardRejectReason, @PathVariable Integer id) throws URISyntaxException {		
		return carryForwardRejectReasonService.editCarryForwardRejectReason(newCarryForwardRejectReason, id);
	}	

	
	@DeleteMapping("/carryforwardrejectreason/{id}")
	ResponseEntity<CarryForwardRejectReason> deleteCarryForwardRejectReason(@PathVariable Integer id) {
		carryForwardRejectReasonService.deleteCarryForwardRejectReason(id);		
		return ResponseEntity.noContent().build();
	}
		
}
