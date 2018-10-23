package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.CarryForwardRejectReason;
public interface CarryForwardRejectReasonService {
	
	ResponseEntity<?> addCarryForwardRejectReason(CarryForwardRejectReason CarryForwardRejectReason) throws URISyntaxException;
	Resources<Resource<CarryForwardRejectReason>>  getAllCarryForwardRejectReasons();
	Resource <CarryForwardRejectReason> getCarryForwardRejectReasonById(Integer id);
	void deleteCarryForwardRejectReason(Integer id);
	ResponseEntity<?> editCarryForwardRejectReason(CarryForwardRejectReason CarryForwardRejectReason, Integer id) throws URISyntaxException;
}
