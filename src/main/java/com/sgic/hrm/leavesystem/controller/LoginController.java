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

import com.sgic.hrm.leavesystem.entity.Login;
import com.sgic.hrm.leavesystem.service.LoginService;

@RestController

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/login")
	ResponseEntity<?> addLogin(@RequestBody Login newLogin) throws URISyntaxException {
		return loginService.addLogin(newLogin);		
	}
	
	
	@GetMapping("/login/{id}")
	public Resource <Login> getLoginById(@PathVariable Integer id) {		
		return loginService.getLoginById(id);		
	}	

	
	@GetMapping("/login")
	public Resources<Resource<Login>> getAllLogins() {
		return loginService.getAllLogins();
	}
	
	
	@PutMapping("/login/{id}")
	ResponseEntity<?> editLogin(@RequestBody Login newLogin, @PathVariable Integer id) throws URISyntaxException {		
		return loginService.editLogin(newLogin, id);
	}	

	
	@DeleteMapping("/login/{id}")
	ResponseEntity<Login> deleteLogin(@PathVariable Integer id) {
		loginService.deleteLogin(id);		
		return ResponseEntity.noContent().build();
	}
		
}
