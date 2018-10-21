package com.sgic.hrm.leavesystem.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.hrm.leavesystem.entity.Leave;
import com.sgic.hrm.leavesystem.service.LeaveService;

@RestController

public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	
	@PostMapping("/leave")
	ResponseEntity<?> addLeave(@RequestBody Leave newLeave) throws URISyntaxException {
		return leaveService.addLeave(newLeave);		
	}
	
	
	@GetMapping("/leave/{id}")
	public Resource <Leave> getLeaveById(@PathVariable Integer id) {		
		return leaveService.getLeaveById(id);		
	}	

	
	@GetMapping("/leave")
	public Resources<Resource<Leave>> getAllLeaves() {
		return leaveService.getAllLeaves();
	}
	
	
	@PutMapping("/leave/{id}")
	ResponseEntity<?> editLeave(@RequestBody Leave newLeave, @PathVariable Integer id) throws URISyntaxException {		
		return leaveService.editLeave(newLeave, id);
	}	

	
	@DeleteMapping("/leave/{id}")
	ResponseEntity<Leave> deleteLeave(@PathVariable Integer id) {
		leaveService.deleteLeave(id);		
		return ResponseEntity.noContent().build();
	}
	
	
//	@GetMapping("/leave/{userId}/{leaveId}")
//	public Resource <Leave> getLeaveByLeaveTypeAndByUser(@PathVariable Integer userId,@PathVariable Integer leaveId ) {		
//		return leaveService.getLeaveByLeaveTypeAndByUser(userId, leaveId);		
//	}	
	
	
	@GetMapping("/leave/user/{userId}")
	public Resources<Resource<Leave>> getLeaveByUser(@PathVariable Integer userId) {
		return leaveService.getLeaveByUser(userId);
	}
	
	@GetMapping("/leave/{uid}/{lid}")
	public ResponseEntity<Float> findRemaingDays(@PathVariable("uid") int uid, @PathVariable("lid") int lid) {
		
		return new ResponseEntity<>(leaveService.getRemainingDaysByLeaveTypeAndByUser(uid, lid), HttpStatus.OK);
	}
		
}
