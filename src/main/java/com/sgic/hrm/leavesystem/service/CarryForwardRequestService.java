package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.CarryForwardRequest;

public interface CarryForwardRequestService {
	
	 ResponseEntity<?> addCarryForwardRequest(CarryForwardRequest carryForwardRequest) throws URISyntaxException;
	 Resources<Resource<CarryForwardRequest>> getAllCarryForwardRequests();
	 ResponseEntity<?> editCarryForwardRequest(CarryForwardRequest carryForwardRequest, Integer id) throws URISyntaxException ;
	 void deleteCarryForwardRequest(Integer id);
	 Resource <CarryForwardRequest> getCarryForwardRequestById(Integer id);

}
