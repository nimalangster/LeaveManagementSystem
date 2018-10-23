package com.sgic.hrm.leavesystem.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgic.hrm.leavesystem.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer>{
	
	@Query("SELECT lr FROM LeaveRequest AS lr WHERE lr.userId.id=?1 and lr.statusId.id=?2")
	List<LeaveRequest> getLeaveRequestByUserAndStatus(Integer userId, Integer statusId);
	
//	//get details of leave request by user id
//	public Iterable<LeaveRequest> findByUserId(User id);

	// date wise pick the leave request records
	@Query("select lr from LeaveRequest as lr where lr.startDate <= ?1 AND lr.endDate >=?1")
	List<LeaveRequest> getLeaveRequestsByDate(ZonedDateTime date);

	@Query("select lr from LeaveRequest as lr where lr.userId.id = ?1")
	List<LeaveRequest> getLeaveRequestsByUser(Integer id);
	
}
