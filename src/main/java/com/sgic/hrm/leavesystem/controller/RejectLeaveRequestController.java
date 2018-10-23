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

import com.sgic.hrm.leavesystem.entity.RejectLeaveRequest;
import com.sgic.hrm.leavesystem.service.RejectLeaveRequestService;

@RestController

public class RejectLeaveRequestController {
	
	@Autowired
	private RejectLeaveRequestService rejectLeaveRequestService;
	
	
	@PostMapping("/rejectleaverequest")
	ResponseEntity<?> addRejectLeaveRequest(@RequestBody RejectLeaveRequest newRejectLeaveRequest) throws URISyntaxException {
		return rejectLeaveRequestService.addRejectLeaveRequest(newRejectLeaveRequest);		
	}
	
	
	@GetMapping("/rejectleaverequest/{id}")
	public Resource <RejectLeaveRequest> getRejectLeaveRequestById(@PathVariable Integer id) {		
		return rejectLeaveRequestService.getRejectLeaveRequestById(id);		
	}	

	
	@GetMapping("/rejectleaverequest")
	public Resources<Resource<RejectLeaveRequest>> getAllRejectLeaveRequests() {
		return rejectLeaveRequestService.getAllRejectLeaveRequests();
	}
	
	
	@PutMapping("/rejectleaverequest/{id}")
	ResponseEntity<?> editRejectLeaveRequest(@RequestBody RejectLeaveRequest newRejectLeaveRequest, @PathVariable Integer id) throws URISyntaxException {		
		return rejectLeaveRequestService.editRejectLeaveRequest(newRejectLeaveRequest, id);
	}	

	
	@DeleteMapping("/rejectleaverequest/{id}")
	ResponseEntity<RejectLeaveRequest> deleteRejectLeaveRequest(@PathVariable Integer id) {
		rejectLeaveRequestService.deleteRejectLeaveRequest(id);		
		return ResponseEntity.noContent().build();
	}
		
}
