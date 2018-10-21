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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.hrm.leavesystem.entity.LeaveType;
import com.sgic.hrm.leavesystem.service.LeaveTypeService;

@RestController

public class LeaveTypeController {
	
	@Autowired
	private LeaveTypeService leaveTypeService;
	
	
	@PostMapping("/leavetype")
	ResponseEntity<?> addLeaveType(@RequestBody LeaveType newLeaveType) throws URISyntaxException {
		return leaveTypeService.addLeaveType(newLeaveType);		
	}
	
	
	@GetMapping("/leavetype/{id}")
	public Resource <LeaveType> getLeaveTypeById(@PathVariable Integer id) {		
		return leaveTypeService.getLeaveTypeById(id);		
	}	

	
	@GetMapping("/leavetype")
	public Resources<Resource<LeaveType>> getAllLeaveTypes() {
		return leaveTypeService.getAllLeaveTypes();
	}
	
	
	@PutMapping("/leavetype/{id}")
	ResponseEntity<?> editLeaveType(@RequestBody LeaveType newLeaveType, @PathVariable Integer id) throws URISyntaxException {		
		return leaveTypeService.editLeaveType(newLeaveType, id);
	}	

	
	@DeleteMapping("/leavetype/{id}")
	ResponseEntity<LeaveType> deleteLeaveType(@PathVariable Integer id) {
		leaveTypeService.deleteLeaveType(id);		
		return ResponseEntity.noContent().build();
	}
		
}
