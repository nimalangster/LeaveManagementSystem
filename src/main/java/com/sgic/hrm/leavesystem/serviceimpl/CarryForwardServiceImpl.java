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

import com.sgic.hrm.leavesystem.controller.CarryForwardController;
import com.sgic.hrm.leavesystem.entity.CarryForward;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.CarryForwardRepository;
import com.sgic.hrm.leavesystem.resourceassembler.CarryForwardResourceAssembler;
import com.sgic.hrm.leavesystem.service.CarryForwardService;


@Service
public class CarryForwardServiceImpl implements CarryForwardService {
	
	@Autowired
	private CarryForwardRepository carryForwardRepository;	
	@Autowired
	private CarryForwardResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addCarryForward(@RequestBody CarryForward newCarryForward) throws URISyntaxException {

		Resource<CarryForward> resource = assembler.toResource(carryForwardRepository.save(newCarryForward));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<CarryForward>> getAllCarryForwards() {
		
		List<Resource<CarryForward>> CarryForwards = carryForwardRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(CarryForwards,
			linkTo(methodOn(CarryForwardController.class).getAllCarryForwards()).withSelfRel());
	}
	
	@Override	
	public Resource<CarryForward> getCarryForwardById(Integer id) {
		CarryForward CarryForward = carryForwardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForward.class.getSimpleName()));

		return assembler.toResource(CarryForward);
		
	}	 
	@Override
	public ResponseEntity<?> editCarryForward(@RequestBody CarryForward newCarryForward, @PathVariable Integer id) throws URISyntaxException {

		CarryForward carryForward = carryForwardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForward.class.getSimpleName()));		

		newCarryForward.setId(id);
		CarryForward updatedCarryForward = carryForwardRepository.save(newCarryForward);	
		Resource<CarryForward> resource = assembler.toResource(updatedCarryForward);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteCarryForward(Integer id) {	
		CarryForward carryForward = carryForwardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForward.class.getSimpleName()));
		carryForwardRepository.deleteById(id);		
	}

}
