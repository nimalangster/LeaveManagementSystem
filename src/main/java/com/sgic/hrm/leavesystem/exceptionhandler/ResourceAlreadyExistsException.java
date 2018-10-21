package com.sgic.hrm.leavesystem.exceptionhandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceAlreadyExistsException extends RuntimeException {

	public ResourceAlreadyExistsException(Integer id,String resourceName) {
		super("Resource '" + resourceName +"' already exists with id " + id);
	}

	
}
