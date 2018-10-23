package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.RejectLeaveRequest;
public interface RejectLeaveRequestService {
	
	ResponseEntity<?> addRejectLeaveRequest(RejectLeaveRequest rejectLeaveRequest) throws URISyntaxException;
	Resources<Resource<RejectLeaveRequest>>  getAllRejectLeaveRequests();
	Resource <RejectLeaveRequest> getRejectLeaveRequestById(Integer id);
	void deleteRejectLeaveRequest(Integer id);
	ResponseEntity<?> editRejectLeaveRequest(RejectLeaveRequest rejectLeaveRequest, Integer id) throws URISyntaxException;
}
