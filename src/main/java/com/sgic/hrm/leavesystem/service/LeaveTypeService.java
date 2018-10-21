package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.LeaveType;
public interface LeaveTypeService {
	
	ResponseEntity<?> addLeaveType(LeaveType LeaveType) throws URISyntaxException;
	Resources<Resource<LeaveType>>  getAllLeaveTypes();
	Resource <LeaveType> getLeaveTypeById(Integer id);
	void deleteLeaveType(Integer id);
	ResponseEntity<?> editLeaveType(LeaveType leaveType, Integer id) throws URISyntaxException;
}
