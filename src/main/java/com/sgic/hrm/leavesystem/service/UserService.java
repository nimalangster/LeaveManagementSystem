package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.User;
public interface UserService {
	
	ResponseEntity<?> addUser(User User) throws URISyntaxException;
	Resources<Resource<User>>  getAllUsers();
	Resource <User> getUserById(Integer id);
	void deleteUser(Integer id);
	ResponseEntity<?> editUser(User user, Integer id) throws URISyntaxException;
}
