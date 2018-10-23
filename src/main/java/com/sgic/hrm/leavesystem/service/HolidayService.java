package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.Holiday;

public interface HolidayService {
	
	ResponseEntity<?> addHoliday(Holiday holiday) throws URISyntaxException;
	Resources<Resource<Holiday>>  getAllHolidays();
	Resource <Holiday> getHolidayById(Integer id);
	void deleteHoliday(Integer id);
	ResponseEntity<?> editHoliday(Holiday holiday, Integer id) throws URISyntaxException;

}
