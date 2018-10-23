package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.Status;
public interface StatusService {
	
	ResponseEntity<?> addStatus(Status status) throws URISyntaxException;
	Resources<Resource<Status>>  getAllStatuses();
	Resource <Status> getStatusById(Integer id);
	void deleteStatus(Integer id);
	ResponseEntity<?> editStatus(Status status, Integer id) throws URISyntaxException;
}
