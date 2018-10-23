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

import com.sgic.hrm.leavesystem.controller.StatusController;
import com.sgic.hrm.leavesystem.entity.Status;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.StatusRepository;
import com.sgic.hrm.leavesystem.resourceassembler.StatusResourceAssembler;
import com.sgic.hrm.leavesystem.service.StatusService;


@Service
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusRepository statusRepository;	
	@Autowired
	private StatusResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addStatus(@RequestBody Status newStatus) throws URISyntaxException {

		Resource<Status> resource = assembler.toResource(statusRepository.save(newStatus));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<Status>> getAllStatuses() {
		
		List<Resource<Status>> Statuss = statusRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(Statuss,
			linkTo(methodOn(StatusController.class).getAllStatuses()).withSelfRel());
	}
	
	@Override	
	public Resource<Status> getStatusById(Integer id) {
		Status Status = statusRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Status.class.getSimpleName()));

		return assembler.toResource(Status);
		
	}	 
	@Override
	public ResponseEntity<?> editStatus(@RequestBody Status newStatus, @PathVariable Integer id) throws URISyntaxException {

		Status status = statusRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Status.class.getSimpleName()));		

		newStatus.setId(id);
		Status updatedStatus = statusRepository.save(newStatus);	
		Resource<Status> resource = assembler.toResource(updatedStatus);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteStatus(Integer id) {	
		Status status = statusRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Status.class.getSimpleName()));
		statusRepository.deleteById(id);		
	}

}
