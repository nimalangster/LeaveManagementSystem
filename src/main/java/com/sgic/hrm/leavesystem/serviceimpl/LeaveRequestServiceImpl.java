package com.sgic.hrm.leavesystem.serviceimpl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sgic.hrm.leavesystem.controller.LeaveRequestController;
import com.sgic.hrm.leavesystem.entity.LeaveRequest;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.LeaveRequestRepository;
import com.sgic.hrm.leavesystem.resourceassembler.LeaveRequestResourceAssembler;
import com.sgic.hrm.leavesystem.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService{
	
	@Autowired
	private LeaveRequestRepository LeaveRequestRepository;	
	@Autowired
	private LeaveRequestResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addLeaveRequest(@RequestBody LeaveRequest newLeaveRequest) throws URISyntaxException {

		Resource<LeaveRequest> resource = assembler.toResource(LeaveRequestRepository.save(newLeaveRequest));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<LeaveRequest>> getAllLeaveRequests() {
		
		List<Resource<LeaveRequest>> LeaveRequests = LeaveRequestRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(LeaveRequests,
			linkTo(methodOn(LeaveRequestController.class).getAllLeaveRequests()).withSelfRel());
	}
	
	@Override	
	public Resource<LeaveRequest> getLeaveRequestById(Integer id) {
		LeaveRequest LeaveRequest = LeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,LeaveRequest.class.getSimpleName()));

		return assembler.toResource(LeaveRequest);
		
	}	 
	@Override
	public ResponseEntity<?> editLeaveRequest(@RequestBody LeaveRequest newLeaveRequest, @PathVariable Integer id) throws URISyntaxException {

		LeaveRequest LeaveRequest = LeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,LeaveRequest.class.getSimpleName()));		

		newLeaveRequest.setId(id);
		LeaveRequest updatedLeaveRequest = LeaveRequestRepository.save(newLeaveRequest);	
		Resource<LeaveRequest> resource = assembler.toResource(updatedLeaveRequest);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteLeaveRequest(Integer id) {	
		LeaveRequest leaveRequest = LeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,LeaveRequest.class.getSimpleName()));
		LeaveRequestRepository.deleteById(id);		
	}
	
	@Override
	public Resources<Resource<LeaveRequest>> getLeaveRequestsByUserAndStatus(Integer userId, Integer statusId){
	
		List<Resource<LeaveRequest>> LeaveRequests = LeaveRequestRepository.getLeaveRequestByUserAndStatus(userId, statusId).stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(LeaveRequests,
			linkTo(methodOn(LeaveRequestController.class).getAllLeaveRequests()).withSelfRel());

		}
	
	@Override
	public Resources<Resource<LeaveRequest>> getLeaveRequestsByDate(ZonedDateTime date){
		
		List<Resource<LeaveRequest>> LeaveRequests = LeaveRequestRepository.getLeaveRequestsByDate(date).stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(LeaveRequests,
			linkTo(methodOn(LeaveRequestController.class).getAllLeaveRequests()).withSelfRel());
	}

	
	@Override
	public Resources<Resource<LeaveRequest>> getLeaveRequestsByUser(Integer id){
		
		List<Resource<LeaveRequest>> LeaveRequests = LeaveRequestRepository.getLeaveRequestsByUser(id).stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(LeaveRequests,
			linkTo(methodOn(LeaveRequestController.class).getAllLeaveRequests()).withSelfRel());
		
	}
	}
