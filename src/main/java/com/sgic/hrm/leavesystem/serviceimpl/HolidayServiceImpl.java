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

import com.sgic.hrm.leavesystem.controller.HolidayController;
import com.sgic.hrm.leavesystem.entity.Holiday;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.HolidayRepository;
import com.sgic.hrm.leavesystem.resourceassembler.HolidayResourceAssembler;
import com.sgic.hrm.leavesystem.service.HolidayService;


@Service
public class HolidayServiceImpl implements HolidayService {
	
	@Autowired
	private HolidayRepository holidayRepository;	
	@Autowired
	private HolidayResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addHoliday(@RequestBody Holiday newHoliday) throws URISyntaxException {

		Resource<Holiday> resource = assembler.toResource(holidayRepository.save(newHoliday));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<Holiday>> getAllHolidays() {
		
		List<Resource<Holiday>> holidays = holidayRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(holidays,
			linkTo(methodOn(HolidayController.class).getAllHolidays()).withSelfRel());
	}
	
	@Override	
	public Resource<Holiday> getHolidayById(Integer id) {
		Holiday holiday = holidayRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Holiday.class.getSimpleName()));

		return assembler.toResource(holiday);
		
	}	 
	@Override
	public ResponseEntity<?> editHoliday(@RequestBody Holiday newHoliday, @PathVariable Integer id) throws URISyntaxException {

		Holiday holiday = holidayRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Holiday.class.getSimpleName()));		

		newHoliday.setId(id);
		Holiday updatedHoliday = holidayRepository.save(newHoliday);	
		Resource<Holiday> resource = assembler.toResource(updatedHoliday);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteHoliday(Integer id) {	
		Holiday Holiday = holidayRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Holiday.class.getSimpleName()));
		holidayRepository.deleteById(id);		
	}

}
