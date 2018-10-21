package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.Role;
public interface RoleService {
	
	ResponseEntity<?> addRole(Role role) throws URISyntaxException;
	Resources<Resource<Role>>  getAllRoles();
	Resource <Role> getRoleById(Integer id);
	void deleteRole(Integer id);
	ResponseEntity<?> editRole(Role role, Integer id) throws URISyntaxException;
}
