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

import com.sgic.hrm.leavesystem.controller.CarryForwardRejectReasonController;
import com.sgic.hrm.leavesystem.entity.CarryForwardRejectReason;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.CarryForwardRejectReasonRepository;
import com.sgic.hrm.leavesystem.resourceassembler.CarryForwardRejectReasonResourceAssembler;
import com.sgic.hrm.leavesystem.service.CarryForwardRejectReasonService;


@Service
public class CarryForwardRejectReasonServiceImpl implements CarryForwardRejectReasonService {
	
	@Autowired
	private CarryForwardRejectReasonRepository carryForwardRejectReasonRepository;	
	@Autowired
	private CarryForwardRejectReasonResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addCarryForwardRejectReason(@RequestBody CarryForwardRejectReason newCarryForwardRejectReason) throws URISyntaxException {

		Resource<CarryForwardRejectReason> resource = assembler.toResource(carryForwardRejectReasonRepository.save(newCarryForwardRejectReason));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<CarryForwardRejectReason>> getAllCarryForwardRejectReasons() {
		
		List<Resource<CarryForwardRejectReason>> carryForwardRejectReasons = carryForwardRejectReasonRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(carryForwardRejectReasons,
			linkTo(methodOn(CarryForwardRejectReasonController.class).getAllCarryForwardRejectReasons()).withSelfRel());
	}
	
	@Override	
	public Resource<CarryForwardRejectReason> getCarryForwardRejectReasonById(Integer id) {
		CarryForwardRejectReason carryForwardRejectReason = carryForwardRejectReasonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForwardRejectReason.class.getSimpleName()));

		return assembler.toResource(carryForwardRejectReason);
		
	}	 
	@Override
	public ResponseEntity<?> editCarryForwardRejectReason(@RequestBody CarryForwardRejectReason newCarryForwardRejectReason, @PathVariable Integer id) throws URISyntaxException {

		CarryForwardRejectReason carryForwardRejectReason = carryForwardRejectReasonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForwardRejectReason.class.getSimpleName()));		

		newCarryForwardRejectReason.setId(id);
		CarryForwardRejectReason updatedCarryForwardRejectReason = carryForwardRejectReasonRepository.save(newCarryForwardRejectReason);	
		Resource<CarryForwardRejectReason> resource = assembler.toResource(updatedCarryForwardRejectReason);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteCarryForwardRejectReason(Integer id) {	
		CarryForwardRejectReason carryForwardRejectReason = carryForwardRejectReasonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,CarryForwardRejectReason.class.getSimpleName()));
		carryForwardRejectReasonRepository.deleteById(id);		
	}

}
