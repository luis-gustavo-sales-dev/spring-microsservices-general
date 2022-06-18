package br.dev.luisgustavosales.generatepassword.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.generatepassword.config.Utilities;
import br.dev.luisgustavosales.generatepassword.dtos.CreateOrUpdatePasswordGroupDTO;
import br.dev.luisgustavosales.generatepassword.entities.PasswordGroup;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions.GroupAlreadyExistsException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions.GroupIsUsedOnPasswordInfoException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions.GroupNotFoundException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions.NotAuthorizedGroupException;
import br.dev.luisgustavosales.generatepassword.repositories.PasswordInfoRepository;
import br.dev.luisgustavosales.generatepassword.services.PasswordGroupService;

@RestController
@RequestMapping(value = "/groups")
public class PasswordGroupController {
	
	@Autowired
	private Utilities utilities;
	
	@Autowired
	private PasswordGroupService passwordGroupService;
	
	@Autowired
	private PasswordInfoRepository passwordInfoRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<PasswordGroup> findGroupById(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers) {
		
		
		var pg = passwordGroupService.findById(id);
		
		if (pg == null) {
			// Must throw an PasswordGroupNotFoundException
			throw new GroupNotFoundException("Group id " + id + " not found!");
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), headers.get("username"));
		
		if (!canAccess) {
			// Must return an PasswordGroupNotAuthorizedException
			throw new NotAuthorizedGroupException(
					"You don't have permission to access group with id: " + id);
		}
		
		return ResponseEntity.ok(pg);
		
	}
	
	@GetMapping
	public ResponseEntity<List<PasswordGroup>> findGroupByParams(
			@RequestParam String _name,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findByNameContainingAndUsername(_name, username);
		
		if (pg == null) {
			// Should I return an empty List or a response without body?
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pg);
		
	}


	@PostMapping
	public ResponseEntity<PasswordGroup> createGroup(
			@RequestBody CreateOrUpdatePasswordGroupDTO createPasswordGroupDTO,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findByNameAndUsername(createPasswordGroupDTO.getName(), username);
		
		if (pg != null) {
			// Must throw an PasswordGroupAlreadyExistsException
			throw new GroupAlreadyExistsException("Group name " + 
						createPasswordGroupDTO.getName() + " already exists!");
		}
		
		// It's so important.
		/* Without this line an user can set another username for his PasswordGroup */
		var passwordGroup = new PasswordGroup();
		
		passwordGroup.setName(createPasswordGroupDTO.getName());
		passwordGroup.setUsername(username);
		
		var pgc = passwordGroupService.create(passwordGroup);
		return ResponseEntity.ok(pgc);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PasswordGroup> updateGroup(
			@PathVariable Long id,
			@RequestBody CreateOrUpdatePasswordGroupDTO updatePasswordGroup,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findById(id);
		
		if (pg == null) {
			// Must throw an PasswordGroupNotFoundException
			throw new GroupNotFoundException("Group id " + id + " not found!");
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), username);
		
		
		if (!canAccess) {
			// Must return an PasswordGroupNotAuthorizedException
			throw new NotAuthorizedGroupException(
					"You don't have permission to access group with id: " + id);
		}
		
		// It's so important.
		/* Without this line an user can set another username for his PasswordGroup */
		
		pg.setName(updatePasswordGroup.getName());
		
		var pgu = passwordGroupService.update(pg);
		
		return ResponseEntity.ok(pgu);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGroup(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers
			){
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findById(id);
		
		if (pg == null) {
			// Must throw an PasswordGroupNotFoundException
			throw new GroupNotFoundException("Group id " + id + " not found!");
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), username);
		
		
		if (!canAccess) {
			// Must return an PasswordGroupNotAuthorizedException
			throw new NotAuthorizedGroupException(
					"You don't have permission to access group with id: " + id);
		}
		
		// Find password group id on Password Info cause of reference integrity
		// Can not delete if exists a reference in Password Info
		
		var pi = passwordInfoRepository.findByPasswordGroupId(pg.getId());
		
		// System.out.println("PasswordInfo: " + pi.get());
		
		if (pi.isPresent()) {
			// System.out.println("PasswordInfo: " + pi.get());
			// Must return an PasswordGroupIsUsedOnPasswordInfoException
			throw new GroupIsUsedOnPasswordInfoException("This group is used for " +
							pi.get().getName() + " password info.");
		}
		
		passwordGroupService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
		
	}
}
