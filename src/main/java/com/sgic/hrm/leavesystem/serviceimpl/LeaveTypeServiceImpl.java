package com.sgic.hrm.leavesystem.serviceimpl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sgic.hrm.leavesystem.controller.LeaveTypeController;
import com.sgic.hrm.leavesystem.entity.LeaveType;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.LeaveTypeRepository;
import com.sgic.hrm.leavesystem.resourceassembler.LeaveTypeResourceAssembler;
import com.sgic.hrm.leavesystem.service.LeaveTypeService;




@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;	
	@Autowired
	private LeaveTypeResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addLeaveType(@RequestBody LeaveType newLeaveType) throws URISyntaxException {

		Resource<LeaveType> resource = assembler.toResource(leaveTypeRepository.save(newLeaveType));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<LeaveType>> getAllLeaveTypes() {
		
		List<Resource<LeaveType>> LeaveTypes = leaveTypeRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(LeaveTypes,
			linkTo(methodOn(LeaveTypeController.class).getAllLeaveTypes()).withSelfRel());
	}
	
	@Override	
	public Resource<LeaveType> getLeaveTypeById(Integer id) {
		LeaveType LeaveType = leaveTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,LeaveType.class.getSimpleName()));

		return assembler.toResource(LeaveType);
		
	}	 
	@Override
	public ResponseEntity<?> editLeaveType(@RequestBody LeaveType newLeaveType, @PathVariable Integer id) throws URISyntaxException {

		LeaveType LeaveType = leaveTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,LeaveType.class.getSimpleName()));		

		newLeaveType.setId(id);
		LeaveType updatedLeaveType = leaveTypeRepository.save(newLeaveType);	
		Resource<LeaveType> resource = assembler.toResource(updatedLeaveType);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteLeaveType(Integer id) {	
		LeaveType leaveType = leaveTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,LeaveType.class.getSimpleName()));
		leaveTypeRepository.deleteById(id);		
	}

}
