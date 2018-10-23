package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;
import java.time.ZonedDateTime;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.LeaveRequest;

public interface LeaveRequestService {
	
	ResponseEntity<?> addLeaveRequest(LeaveRequest LeaveRequest) throws URISyntaxException;
	Resources<Resource<LeaveRequest>>  getAllLeaveRequests();
	Resource <LeaveRequest> getLeaveRequestById(Integer id);
	void deleteLeaveRequest(Integer id);
	ResponseEntity<?> editLeaveRequest(LeaveRequest leaveRequest, Integer id) throws URISyntaxException;
	
	Resources<Resource<LeaveRequest>> getLeaveRequestsByUserAndStatus(Integer userId, Integer statusId);
	Resources<Resource<LeaveRequest>> getLeaveRequestsByDate(ZonedDateTime date);
	Resources<Resource<LeaveRequest>> getLeaveRequestsByUser(Integer id);
}
