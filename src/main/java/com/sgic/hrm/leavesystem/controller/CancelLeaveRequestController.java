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

import com.sgic.hrm.leavesystem.entity.CancelLeaveRequest;
import com.sgic.hrm.leavesystem.service.CancelLeaveRequestService;

@RestController

public class CancelLeaveRequestController {
	
	@Autowired
	private CancelLeaveRequestService cancelLeaveRequestService;
	
	
	@PostMapping("/cancelleaverequest")
	ResponseEntity<?> addCancelLeaveRequest(@RequestBody CancelLeaveRequest newCancelLeaveRequest) throws URISyntaxException {
		return cancelLeaveRequestService.addCancelLeaveRequest(newCancelLeaveRequest);		
	}
	
	
	@GetMapping("/cancelleaverequest/{id}")
	public Resource <CancelLeaveRequest> getCancelLeaveRequestById(@PathVariable Integer id) {		
		return cancelLeaveRequestService.getCancelLeaveRequestById(id);		
	}	

	
	@GetMapping("/cancelleaverequest")
	public Resources<Resource<CancelLeaveRequest>> getAllCancelLeaveRequests() {
		return cancelLeaveRequestService.getAllCancelLeaveRequests();
	}
	
	
	@PutMapping("/cancelleaverequest/{id}")
	ResponseEntity<?> editCancelLeaveRequest(@RequestBody CancelLeaveRequest newCancelLeaveRequest, @PathVariable Integer id) throws URISyntaxException {		
		return cancelLeaveRequestService.editCancelLeaveRequest(newCancelLeaveRequest, id);
	}	

	
	@DeleteMapping("/cancelleaverequest/{id}")
	ResponseEntity<CancelLeaveRequest> deleteCancelLeaveRequest(@PathVariable Integer id) {
		cancelLeaveRequestService.deleteCancelLeaveRequest(id);		
		return ResponseEntity.noContent().build();
	}
		
}
