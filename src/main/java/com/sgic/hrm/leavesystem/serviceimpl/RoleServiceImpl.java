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

import com.sgic.hrm.leavesystem.controller.RoleController;
import com.sgic.hrm.leavesystem.entity.Role;
import com.sgic.hrm.leavesystem.exceptionhandler.ResourceNotFoundException;
import com.sgic.hrm.leavesystem.repository.RoleRepository;
import com.sgic.hrm.leavesystem.resourceassembler.RoleResourceAssembler;
import com.sgic.hrm.leavesystem.service.RoleService;




@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;	
	@Autowired
	private RoleResourceAssembler assembler;
	
	
	@Override	
	public ResponseEntity<?> addRole(@RequestBody Role newRole) throws URISyntaxException {

		Resource<Role> resource = assembler.toResource(roleRepository.save(newRole));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	@Override	
	public	Resources<Resource<Role>> getAllRoles() {
		
		List<Resource<Role>> roles = roleRepository.findAll().stream()
				.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(roles,
			linkTo(methodOn(RoleController.class).getAllRoles()).withSelfRel());
	}
	
	@Override	
	public Resource<Role> getRoleById(Integer id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Role.class.getSimpleName()));

		return assembler.toResource(role);
		
	}	 
	@Override
	public ResponseEntity<?> editRole(@RequestBody Role newRole, @PathVariable Integer id) throws URISyntaxException {

		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Role.class.getSimpleName()));		

		newRole.setId(id);
		Role updatedRole = roleRepository.save(newRole);	
		Resource<Role> resource = assembler.toResource(updatedRole);
		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	@Override
	public void deleteRole(Integer id) {	
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id,Role.class.getSimpleName()));
		roleRepository.deleteById(id);		
	}

}
