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

import com.sgic.hrm.leavesystem.controller.LoginController;
import com.sgic.hrm.leavesystem.entity.Login;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.LoginRepository;
import com.sgic.hrm.leavesystem.resourceassembler.LoginResourceAssembler;
import com.sgic.hrm.leavesystem.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;	
	@Autowired
	private LoginResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addLogin(@RequestBody Login newLogin) throws URISyntaxException {

		Resource<Login> resource = assembler.toResource(loginRepository.save(newLogin));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<Login>> getAllLogins() {
		
		List<Resource<Login>> Logins = loginRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(Logins,
			linkTo(methodOn(LoginController.class).getAllLogins()).withSelfRel());
	}
	
	@Override	
	public Resource<Login> getLoginById(Integer id) {
		Login login = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Login.class.getSimpleName()));

		return assembler.toResource(login);
		
	}	 
	@Override
	public ResponseEntity<?> editLogin(@RequestBody Login newLogin, @PathVariable Integer id) throws URISyntaxException {

		Login Login = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Login.class.getSimpleName()));		

		newLogin.setId(id);
		Login updatedLogin = loginRepository.save(newLogin);	
		Resource<Login> resource = assembler.toResource(updatedLogin);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteLogin(Integer id) {	
		Login login = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Login.class.getSimpleName()));
		loginRepository.deleteById(id);		
	}

}
