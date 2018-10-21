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

import com.sgic.hrm.leavesystem.controller.UserController;
import com.sgic.hrm.leavesystem.entity.User;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.UserRepository;
import com.sgic.hrm.leavesystem.resourceassembler.UserResourceAssembler;
import com.sgic.hrm.leavesystem.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository UserRepository;	
	@Autowired
	private UserResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addUser(@RequestBody User newUser) throws URISyntaxException {

		Resource<User> resource = assembler.toResource(UserRepository.save(newUser));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<User>> getAllUsers() {
		
		List<Resource<User>> users = UserRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(users,
			linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
	}
	
	@Override	
	public Resource<User> getUserById(Integer id) {
		User user = UserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,User.class.getSimpleName()));

		return assembler.toResource(user);
		
	}	 
	@Override
	public ResponseEntity<?> editUser(@RequestBody User newUser, @PathVariable Integer id) throws URISyntaxException {

		User user = UserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,User.class.getSimpleName()));		

		newUser.setId(id);
		User updatedUser = UserRepository.save(newUser);	
		Resource<User> resource = assembler.toResource(updatedUser);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteUser(Integer id) {	
		User user = UserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,User.class.getSimpleName()));
		UserRepository.deleteById(id);		
	}

}
