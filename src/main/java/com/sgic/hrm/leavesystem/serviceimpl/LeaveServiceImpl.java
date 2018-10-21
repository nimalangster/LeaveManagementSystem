package com.sgic.hrm.leavesystem.serviceimpl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sgic.hrm.leavesystem.controller.LeaveController;
import com.sgic.hrm.leavesystem.entity.Leave;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.LeaveRepository;
import com.sgic.hrm.leavesystem.resourceassembler.LeaveResourceAssembler;
import com.sgic.hrm.leavesystem.service.LeaveService;


@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	private LeaveRepository leaveRepository;	
	@Autowired
	private LeaveResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addLeave(@RequestBody Leave newLeave) throws URISyntaxException {

		Resource<Leave> resource = assembler.toResource(leaveRepository.save(newLeave));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<Leave>> getAllLeaves() {
		
		List<Resource<Leave>> Leaves = leaveRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(Leaves,
			linkTo(methodOn(LeaveController.class).getAllLeaves()).withSelfRel());
	}
	
	@Override	
	public Resource<Leave> getLeaveById(Integer id) {
		Leave Leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Leave.class.getSimpleName()));

		return assembler.toResource(Leave);
		
	}	 
	@Override
	public ResponseEntity<?> editLeave(@RequestBody Leave newLeave, @PathVariable Integer id) throws URISyntaxException {

		Leave leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Leave.class.getSimpleName()));		

		newLeave.setId(id);
		Leave updatedLeave = leaveRepository.save(newLeave);	
		Resource<Leave> resource = assembler.toResource(updatedLeave);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteLeave(Integer id) {	
		Leave leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Leave.class.getSimpleName()));
		leaveRepository.deleteById(id);		
	}

	@Override
	public Resource <Leave> getLeaveByLeaveTypeAndByUser(Integer userId, Integer leaveId) {
		
		Leave leave = leaveRepository.getLeaveByLeaveTypeAndByUser(userId, leaveId);
		
		if(leave == null) {
			new ResourceNotFoundException(userId,Leave.class.getSimpleName());
		}
		
		return assembler.toResource(leave);
	}
	
	@Override
	public Float getRemainingDaysByLeaveTypeAndByUser(Integer userId, Integer leaveId) {
		
		Leave leave = leaveRepository.getLeaveByLeaveTypeAndByUser(userId, leaveId);				
		return leave.getRemainDays();
	}

	@Override
	public Resources<Resource<Leave>> getLeaveByUser(Integer userId) {
		
		List<Resource<Leave>> leaves = leaveRepository.getLeaveByUser(userId).stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(leaves,
			linkTo(methodOn(LeaveController.class).getAllLeaves()).withSelfRel());
	}

	

}
