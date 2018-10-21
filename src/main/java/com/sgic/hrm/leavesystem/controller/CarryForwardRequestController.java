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

import com.sgic.hrm.leavesystem.entity.CarryForwardRequest;
import com.sgic.hrm.leavesystem.service.CarryForwardRequestService;

@RestController

public class CarryForwardRequestController {
	
	@Autowired
	private CarryForwardRequestService CarryForwardRequestService;
	
	
	@PostMapping("/carryforwardrequest")
	ResponseEntity<?> addCarryForwardRequest(@RequestBody CarryForwardRequest newCarryForwardRequest) throws URISyntaxException {
		return CarryForwardRequestService.addCarryForwardRequest(newCarryForwardRequest);		
	}
	
	
	@GetMapping("/carryforwardrequest/{id}")
	public Resource <CarryForwardRequest> getCarryForwardRequestById(@PathVariable Integer id) {		
		return CarryForwardRequestService.getCarryForwardRequestById(id);		
	}	

	
	@GetMapping("/carryforwardrequest")
	public Resources<Resource<CarryForwardRequest>> getAllCarryForwardRequests() {
		return CarryForwardRequestService.getAllCarryForwardRequests();
	}
	
	
	@PutMapping("/carryforwardrequest/{id}")
	ResponseEntity<?> editCarryForwardRequest(@RequestBody CarryForwardRequest newCarryForwardRequest, @PathVariable Integer id) throws URISyntaxException {		
		return CarryForwardRequestService.editCarryForwardRequest(newCarryForwardRequest, id);
	}	

	
	@DeleteMapping("/carryforwardrequest/{id}")
	ResponseEntity<CarryForwardRequest> deleteCarryForwardRequest(@PathVariable Integer id) {
		CarryForwardRequestService.deleteCarryForwardRequest(id);		
		return ResponseEntity.noContent().build();
	}
		
}
