package com.sgic.hrm.leavesystem.exceptionhandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(Integer id,String resourceName) {
		super("Could not find '" + resourceName +"' with id " + id);
	}

	
}
