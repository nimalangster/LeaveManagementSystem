package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.CancelLeaveRequest;

	public interface CancelLeaveRequestService {
		
		ResponseEntity<?> addCancelLeaveRequest(CancelLeaveRequest cancelLeaveRequest) throws URISyntaxException;
		Resources<Resource<CancelLeaveRequest>>  getAllCancelLeaveRequests();
		Resource <CancelLeaveRequest> getCancelLeaveRequestById(Integer id);
		void deleteCancelLeaveRequest(Integer id);
		ResponseEntity<?> editCancelLeaveRequest(CancelLeaveRequest cancelLeaveRequest, Integer id) throws URISyntaxException;
	}


 