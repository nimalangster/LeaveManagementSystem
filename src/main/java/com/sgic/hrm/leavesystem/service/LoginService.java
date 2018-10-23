package com.sgic.hrm.leavesystem.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import com.sgic.hrm.leavesystem.entity.Login;
import com.sgic.hrm.leavesystem.entity.Login;

public interface LoginService {
	
	
	ResponseEntity<?> addLogin(Login Login) throws URISyntaxException;
	Resources<Resource<Login>>  getAllLogins();
	Resource <Login> getLoginById(Integer id);
	void deleteLogin(Integer id);
	ResponseEntity<?> editLogin(Login Login, Integer id) throws URISyntaxException;
	

//	boolean getLoginVerification(String LoginName, String password);	
//	String getLogedLoginRoleByLoginName(String LoginName);	
//	String getLoginDepartmentByLoginName(String LoginName);
	
	
}
