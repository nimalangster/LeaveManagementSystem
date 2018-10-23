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

import com.sgic.hrm.leavesystem.controller.CancelLeaveRequestController;
import com.sgic.hrm.leavesystem.entity.CancelLeaveRequest;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.CancelLeaveRequestRepository;
import com.sgic.hrm.leavesystem.resourceassembler.CancelLeaveRequestResourceAssembler;
import com.sgic.hrm.leavesystem.service.CancelLeaveRequestService;


@Service
public class CancelLeaveRequestServiceImpl implements CancelLeaveRequestService {
	
	@Autowired
	private CancelLeaveRequestRepository cancelLeaveRequestRepository;	
	@Autowired
	private CancelLeaveRequestResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addCancelLeaveRequest(@RequestBody CancelLeaveRequest newCancelLeaveRequest) throws URISyntaxException {

		Resource<CancelLeaveRequest> resource = assembler.toResource(cancelLeaveRequestRepository.save(newCancelLeaveRequest));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<CancelLeaveRequest>> getAllCancelLeaveRequests() {
		
		List<Resource<CancelLeaveRequest>> CancelLeaveRequests = cancelLeaveRequestRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(CancelLeaveRequests,
			linkTo(methodOn(CancelLeaveRequestController.class).getAllCancelLeaveRequests()).withSelfRel());
	}
	
	@Override	
	public Resource<CancelLeaveRequest> getCancelLeaveRequestById(Integer id) {
		CancelLeaveRequest CancelLeaveRequest = cancelLeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CancelLeaveRequest.class.getSimpleName()));

		return assembler.toResource(CancelLeaveRequest);
		
	}	 
	@Override
	public ResponseEntity<?> editCancelLeaveRequest(@RequestBody CancelLeaveRequest newCancelLeaveRequest, @PathVariable Integer id) throws URISyntaxException {

		CancelLeaveRequest cancelLeaveRequest = cancelLeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CancelLeaveRequest.class.getSimpleName()));		

		newCancelLeaveRequest.setId(id);
		CancelLeaveRequest updatedCancelLeaveRequest = cancelLeaveRequestRepository.save(newCancelLeaveRequest);	
		Resource<CancelLeaveRequest> resource = assembler.toResource(updatedCancelLeaveRequest);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteCancelLeaveRequest(Integer id) {	
		CancelLeaveRequest cancelLeaveRequest = cancelLeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CancelLeaveRequest.class.getSimpleName()));
		cancelLeaveRequestRepository.deleteById(id);		
	}

}
