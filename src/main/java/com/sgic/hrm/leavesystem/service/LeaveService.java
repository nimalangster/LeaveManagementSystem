package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.Leave;

public interface LeaveService {
	
	ResponseEntity<?> addLeave(Leave leave) throws URISyntaxException;
	Resources<Resource<Leave>>  getAllLeaves();
	Resource <Leave> getLeaveById(Integer id);
	void deleteLeave(Integer id);
	ResponseEntity<?> editLeave(Leave leave, Integer id) throws URISyntaxException;
    
	Resource <Leave> getLeaveByLeaveTypeAndByUser(Integer userId, Integer leaveId);
    Resources<Resource<Leave>> getLeaveByUser(Integer userId);
    Float getRemainingDaysByLeaveTypeAndByUser(Integer userId, Integer leaveId);
	
//	Leave getRemainingDaysByLeaveTypeByUser(Integer userId, Integer leaveId);	
//	List<Leave> getLeaveByUser(Integer userId);
	
// // code done by Jerobert
// 	float increaseRemaingLeaveDays(float numOfDays, int userID, int leaveTypeId);
//
// 	// code done by Jerobert
// 	float decreaseRemaingLeaveDays(float numOfDays, int userID, int leaveTypeId);

}
