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
import br.dev.luisgustavosales.generatepassword.dtos.CreatePasswordGroupDTO;
import br.dev.luisgustavosales.generatepassword.entities.PasswordGroup;
import br.dev.luisgustavosales.generatepassword.services.PasswordGroupService;

@RestController
@RequestMapping(value = "/groups")
public class PasswordGroupController {
	
	@Autowired
	private Utilities utilities;
	
	@Autowired
	private PasswordGroupService passwordGroupService;
	
	@GetMapping
	public ResponseEntity<List<PasswordGroup>> findGroupByParams(
			@RequestParam String _name,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findByNameContainingAndUsername(_name, username);
		
		if (pg == null) {
			// Must throw an PasswordGroupNotFoundException
			return ResponseEntity.notFound().build();
		}
		
		/*var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), headers.get("username"));*/
		
		return ResponseEntity.ok(pg);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<PasswordGroup> findGroupById(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers) {
		
		
		var pg = passwordGroupService.findById(id);
		
		if (pg == null) {
			// Must throw an PasswordGroupNotFoundException
			return ResponseEntity.notFound().build();
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), headers.get("username"));
		
		if (!canAccess) {
			// Must return an PasswordGroupNotAuthorizedException
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pg);
		
	}

	@PostMapping
	public ResponseEntity<PasswordGroup> createGroup(
			@RequestBody CreatePasswordGroupDTO createPasswordGroupDTO,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findByNameAndUsername(createPasswordGroupDTO.getName(), username);
		
		if (pg != null) {
			// Must throw an PasswordGroupAlreadyExistsException
			return ResponseEntity.badRequest().build();
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
			@RequestBody PasswordGroup passwordGroup,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordGroupService.findById(id);
		
		if (pg == null) {
			// Must throw an PasswordGroupNotFoundException
			return ResponseEntity.notFound().build();
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), username);
		
		
		if (!canAccess) {
			// Must return an PasswordGroupNotAuthorizedException
			return ResponseEntity.notFound().build();
		}
		
		// It's so important.
		/* Without this line an user can set another username for his PasswordGroup */
		passwordGroup.setUsername(username);
		
		var pgu = passwordGroupService.update(passwordGroup);
		
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
			return ResponseEntity.notFound().build();
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pg.getUsername(), username);
		
		
		if (!canAccess) {
			// Must return an PasswordGroupNotAuthorizedException
			return ResponseEntity.notFound().build();
		}
		
		passwordGroupService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
		
	}
}
