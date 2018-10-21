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

import com.sgic.hrm.leavesystem.entity.User;
import com.sgic.hrm.leavesystem.service.UserService;

@RestController

public class UserController {
	
	@Autowired
	private UserService UserService;
	
	
	@PostMapping("/user")
	ResponseEntity<?> addUser(@RequestBody User newUser) throws URISyntaxException {
		return UserService.addUser(newUser);		
	}
	
	
	@GetMapping("/user/{id}")
	public Resource <User> getUserById(@PathVariable Integer id) {		
		return UserService.getUserById(id);		
	}	

	
	@GetMapping("/user")
	public Resources<Resource<User>> getAllUsers() {
		return UserService.getAllUsers();
	}
	
	
	@PutMapping("/user/{id}")
	ResponseEntity<?> editUser(@RequestBody User newUser, @PathVariable Integer id) throws URISyntaxException {		
		return UserService.editUser(newUser, id);
	}	

	
	@DeleteMapping("/user/{id}")
	ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		UserService.deleteUser(id);		
		return ResponseEntity.noContent().build();
	}
		
}
