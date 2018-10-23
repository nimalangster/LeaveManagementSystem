package com.sgic.hrm.leavesystem.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.hrm.leavesystem.entity.Holiday;
import com.sgic.hrm.leavesystem.service.HolidayService;

@RestController

public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
	
	@PostMapping("/holiday")
	ResponseEntity<?> addHoliday(@RequestBody Holiday newHoliday) throws URISyntaxException {
		return holidayService.addHoliday(newHoliday);		
	}
	
	
	@GetMapping("/holiday/{id}")
	public Resource <Holiday> getHolidayById(@PathVariable Integer id) {		
		return holidayService.getHolidayById(id);		
	}	

	
	@GetMapping("/holiday")
	public Resources<Resource<Holiday>> getAllHolidays() {
		return holidayService.getAllHolidays();
	}
	
	
	@PutMapping("/holiday/{id}")
	ResponseEntity<?> editHoliday(@RequestBody Holiday newHoliday, @PathVariable Integer id) throws URISyntaxException {		
		return holidayService.editHoliday(newHoliday, id);
	}	

	
	@DeleteMapping("/holiday/{id}")
	ResponseEntity<Holiday> deleteHoliday(@PathVariable Integer id) {
		holidayService.deleteHoliday(id);		
		return ResponseEntity.noContent().build();
	}
		
}
