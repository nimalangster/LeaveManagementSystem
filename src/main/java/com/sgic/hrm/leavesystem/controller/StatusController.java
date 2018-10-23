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

import com.sgic.hrm.leavesystem.entity.Status;
import com.sgic.hrm.leavesystem.service.StatusService;

@RestController

public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	
	@PostMapping("/status")
	ResponseEntity<?> addstatus(@RequestBody Status newstatus) throws URISyntaxException {
		return statusService.addStatus(newstatus);		
	}
	
	
	@GetMapping("/status/{id}")
	public Resource <Status> getStatusById(@PathVariable Integer id) {		
		return statusService.getStatusById(id);		
	}	

	
	@GetMapping("/status")
	public Resources<Resource<Status>> getAllStatuses() {
		return statusService.getAllStatuses();
	}
	
	
	@PutMapping("/status/{id}")
	ResponseEntity<?> editstatus(@RequestBody Status newstatus, @PathVariable Integer id) throws URISyntaxException {		
		return statusService.editStatus(newstatus, id);
	}	

	
	@DeleteMapping("/status/{id}")
	ResponseEntity<Status> deletestatus(@PathVariable Integer id) {
		statusService.deleteStatus(id);		
		return ResponseEntity.noContent().build();
	}
		
}
