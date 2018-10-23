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

import com.sgic.hrm.leavesystem.controller.RejectLeaveRequestController;
import com.sgic.hrm.leavesystem.entity.RejectLeaveRequest;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.RejectLeaveRequestRepository;
import com.sgic.hrm.leavesystem.resourceassembler.RejectLeaveRequestResourceAssembler;
import com.sgic.hrm.leavesystem.service.RejectLeaveRequestService;


@Service
public class RejectLeaveRequestServiceImpl implements RejectLeaveRequestService {
	
	@Autowired
	private RejectLeaveRequestRepository rejectLeaveRequestRepository;	
	@Autowired
	private RejectLeaveRequestResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addRejectLeaveRequest(@RequestBody RejectLeaveRequest newRejectLeaveRequest) throws URISyntaxException {

		Resource<RejectLeaveRequest> resource = assembler.toResource(rejectLeaveRequestRepository.save(newRejectLeaveRequest));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<RejectLeaveRequest>> getAllRejectLeaveRequests() {
		
		List<Resource<RejectLeaveRequest>> RejectLeaveRequests = rejectLeaveRequestRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(RejectLeaveRequests,
			linkTo(methodOn(RejectLeaveRequestController.class).getAllRejectLeaveRequests()).withSelfRel());
	}
	
	@Override	
	public Resource<RejectLeaveRequest> getRejectLeaveRequestById(Integer id) {
		RejectLeaveRequest RejectLeaveRequest = rejectLeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,RejectLeaveRequest.class.getSimpleName()));

		return assembler.toResource(RejectLeaveRequest);
		
	}	 
	@Override
	public ResponseEntity<?> editRejectLeaveRequest(@RequestBody RejectLeaveRequest newRejectLeaveRequest, @PathVariable Integer id) throws URISyntaxException {

		RejectLeaveRequest RejectLeaveRequest = rejectLeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,RejectLeaveRequest.class.getSimpleName()));		

		newRejectLeaveRequest.setId(id);
		RejectLeaveRequest updatedRejectLeaveRequest = rejectLeaveRequestRepository.save(newRejectLeaveRequest);	
		Resource<RejectLeaveRequest> resource = assembler.toResource(updatedRejectLeaveRequest);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteRejectLeaveRequest(Integer id) {	
		RejectLeaveRequest RejectLeaveRequest = rejectLeaveRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,RejectLeaveRequest.class.getSimpleName()));
		rejectLeaveRequestRepository.deleteById(id);		
	}

}
