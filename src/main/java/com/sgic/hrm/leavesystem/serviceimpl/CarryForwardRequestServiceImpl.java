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

import com.sgic.hrm.leavesystem.controller.CarryForwardRequestController;
import com.sgic.hrm.leavesystem.entity.CarryForwardRequest;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceAlreadyExistsException;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.CarryForwardRequestRepository;
import com.sgic.hrm.leavesystem.resourceassembler.CarryForwardRequestResourceAssembler;
import com.sgic.hrm.leavesystem.service.CarryForwardRequestService;

@Service
public class CarryForwardRequestServiceImpl implements CarryForwardRequestService {
	
	@Autowired
	private CarryForwardRequestRepository CarryForwardRequestRepository;	
	@Autowired
	private CarryForwardRequestResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addCarryForwardRequest(@RequestBody CarryForwardRequest newCarryForwardRequest) throws URISyntaxException {

		
//		Optional<CarryForwardRequest> carryForwardRequest = CarryForwardRequestRepository.findById(newCarryForwardRequest.getId());
//		
//		if(carryForwardRequest.isPresent()) {						
//			throw new ResourceAlreadyExistsException(newCarryForwardRequest.getId(),CarryForwardRequest.class.getSimpleName());	
//			}
		
		Resource<CarryForwardRequest> resource = assembler.toResource(CarryForwardRequestRepository.save(newCarryForwardRequest));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<CarryForwardRequest>> getAllCarryForwardRequests() {
		
		List<Resource<CarryForwardRequest>> carryForwardRequests = CarryForwardRequestRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(carryForwardRequests,
			linkTo(methodOn(CarryForwardRequestController.class).getAllCarryForwardRequests()).withSelfRel());
	}
	
	@Override	
	public Resource<CarryForwardRequest> getCarryForwardRequestById(Integer id) {
		CarryForwardRequest CarryForwardRequest = CarryForwardRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForwardRequest.class.getSimpleName()));

		return assembler.toResource(CarryForwardRequest);
		
	}	 
	@Override
	public ResponseEntity<?> editCarryForwardRequest(@RequestBody CarryForwardRequest newCarryForwardRequest, @PathVariable Integer id) throws URISyntaxException {

		CarryForwardRequest carryForwardRequest = CarryForwardRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForwardRequest.class.getSimpleName()));		

		newCarryForwardRequest.setId(id);
		CarryForwardRequest updatedCarryForwardRequest = CarryForwardRequestRepository.save(newCarryForwardRequest);	
		Resource<CarryForwardRequest> resource = assembler.toResource(updatedCarryForwardRequest);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteCarryForwardRequest(Integer id) {	
		CarryForwardRequest CarryForwardRequest = CarryForwardRequestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForwardRequest.class.getSimpleName()));
		CarryForwardRequestRepository.deleteById(id);		
	}

}
