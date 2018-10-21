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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.hrm.leavesystem.entity.Role;
import com.sgic.hrm.leavesystem.service.RoleService;

@RestController

public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/role")
	ResponseEntity<?> addRole(@RequestBody Role newRole) throws URISyntaxException {
		return roleService.addRole(newRole);		
	}
	
	
	@GetMapping("/role/{id}")
	public Resource <Role> getRoleById(@PathVariable Integer id) {		
		return roleService.getRoleById(id);		
	}	

	
	@GetMapping("/role")
	public Resources<Resource<Role>> getAllRoles() {
		return roleService.getAllRoles();
	}
	
	
	@PutMapping("/role/{id}")
	ResponseEntity<?> editRole(@RequestBody Role newRole, @PathVariable Integer id) throws URISyntaxException {		
		return roleService.editRole(newRole, id);
	}	

	
	@DeleteMapping("/role/{id}")
	ResponseEntity<Role> deleteRole(@PathVariable Integer id) {
		roleService.deleteRole(id);		
		return ResponseEntity.noContent().build();
	}
		
}
