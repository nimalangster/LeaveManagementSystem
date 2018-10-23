package com.sgic.hrm.leavesystem.controller;

import java.net.URISyntaxException;
import java.time.ZonedDateTime;

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

import com.sgic.hrm.leavesystem.entity.LeaveRequest;
import com.sgic.hrm.leavesystem.service.LeaveRequestService;

@RestController

public class LeaveRequestController {
	
	@Autowired
	private LeaveRequestService LeaveRequestService;
	
	
	@PostMapping("/leaverequest")
	ResponseEntity<?> addLeaveRequest(@RequestBody LeaveRequest newLeaveRequest) throws URISyntaxException {
		return LeaveRequestService.addLeaveRequest(newLeaveRequest);		
	}
	
	
	@GetMapping("/leaverequest/{id}")
	public Resource <LeaveRequest> getLeaveRequestById(@PathVariable Integer id) {		
		return LeaveRequestService.getLeaveRequestById(id);		
	}	

	
	@GetMapping("/leaverequest")
	public Resources<Resource<LeaveRequest>> getAllLeaveRequests() {
		return LeaveRequestService.getAllLeaveRequests();
	}
	
	
	@PutMapping("/leaverequest/{id}")
	ResponseEntity<?> editLeaveRequest(@RequestBody LeaveRequest newLeaveRequest, @PathVariable Integer id) throws URISyntaxException {		
		return LeaveRequestService.editLeaveRequest(newLeaveRequest, id);
	}	

	
	@DeleteMapping("/leaverequest/{id}")
	ResponseEntity<LeaveRequest> deleteLeaveRequest(@PathVariable Integer id) {
		LeaveRequestService.deleteLeaveRequest(id);		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/leaverequest/byuserandstatus/{userId}/{statusId}")
	public Resources<Resource<LeaveRequest>> getLeaveRequestsByUserAndStatus(@PathVariable Integer userId, @PathVariable Integer statusId) {
		return LeaveRequestService.getLeaveRequestsByUserAndStatus(userId, statusId);
	}
	
	@GetMapping("/leaverequest/bydate/{date}")
	public Resources<Resource<LeaveRequest>> getLeaveRequestsByDate(@PathVariable ZonedDateTime date) {
		return LeaveRequestService.getLeaveRequestsByDate(date);
	}
	
	@GetMapping("/leaverequest/byuser/{userId}")
	public Resources<Resource<LeaveRequest>> getLeaveRequestsByUser(@PathVariable Integer userId) {
		return LeaveRequestService.getAllLeaveRequests();
	}
		
}
