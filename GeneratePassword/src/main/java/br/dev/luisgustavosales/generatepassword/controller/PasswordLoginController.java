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
import br.dev.luisgustavosales.generatepassword.entities.PasswordGroup;
import br.dev.luisgustavosales.generatepassword.entities.PasswordLogin;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions.LoginNotFoundException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions.NotAuthorizedLoginException;
import br.dev.luisgustavosales.generatepassword.services.PasswordLoginService;

@RestController
@RequestMapping(value = "/logins")
public class PasswordLoginController {
	
	@Autowired
	private Utilities utilities;
	
	@Autowired
	private PasswordLoginService passwordLoginService;

	@GetMapping("/{id}")
	public ResponseEntity<PasswordLogin> findLoginById(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers) {
		
		
		var pl = passwordLoginService.findById(id);
		
		if (pl == null) {
			// Must throw an PasswordLoginNotFoundException
			throw new LoginNotFoundException("Login id " + id + " not found.");
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pl.getUsername(), headers.get("username"));
		
		if (!canAccess) {
			// Must return an PasswordLoginNotAuthorizedException
			throw new NotAuthorizedLoginException(
					"You don't have permission to access login with id: " + id);
		}
		
		return ResponseEntity.ok(pl);
		
	}
	
	@GetMapping
	public ResponseEntity<List<PasswordLogin>> findLoginByParams(
			@RequestParam String _name,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordLoginService.findByNameContainingAndUsername(_name, username);
		
		if (pg == null) {
			// Should I return an empty List or a response without body?
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pg);
		
	}

	@PostMapping
	public ResponseEntity<PasswordLogin> createLogin(
			@RequestBody PasswordLogin passwordLogin,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pl = passwordLoginService.findByNameAndUsername(passwordLogin.getName(), username);
		
		if (pl != null) {
			// Must throw an PasswordLoginAlreadyExistsException
			return ResponseEntity.badRequest().build();
		}
		
		// It's so important.
		/* Without this line an user can set another username for his PasswordLogin */
		passwordLogin.setUsername(username);
		
		var plc = passwordLoginService.create(passwordLogin);
		return ResponseEntity.ok(plc);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PasswordLogin> updateLogin(
			@PathVariable Long id,
			@RequestBody PasswordLogin passwordLogin,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pl = passwordLoginService.findById(id);
		
		if (pl == null) {
			// Must throw an PasswordLoginNotFoundException
			throw new LoginNotFoundException("Login id " + id + " not found.");
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pl.getUsername(), username);
		
		
		if (!canAccess) {
			// Must return an PasswordLoginNotAuthorizedException
			return ResponseEntity.notFound().build();
		}
		
		// It's so important.
		/* Without this line an user can set another username for his PasswordLogin */
		passwordLogin.setUsername(username);
		
		var plu = passwordLoginService.update(passwordLogin);
		
		return ResponseEntity.ok(plu);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLogin(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers
			){
		
		var username = headers.get("username");
		
		var pl = passwordLoginService.findById(id);
		
		if (pl == null) {
			// Must throw an PasswordLoginNotFoundException
			throw new LoginNotFoundException("Login id " + id + " not found.");
		}
		
		var canAccess = utilities.canAcessPasswordResource(
				pl.getUsername(), username);
		
		
		if (!canAccess) {
			// Must return an PasswordLoginNotAuthorizedException
			return ResponseEntity.notFound().build();
		}
		
		passwordLoginService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
		
	}

}
