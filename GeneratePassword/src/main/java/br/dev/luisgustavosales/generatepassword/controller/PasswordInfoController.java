package br.dev.luisgustavosales.generatepassword.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.generatepassword.config.Utilities;
import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions.NotAuthorizedPasswordInfoException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions.PasswordInfoNotFoundException;
import br.dev.luisgustavosales.generatepassword.services.PasswordInfoService;

@RestController
@RequestMapping(value = "/passwords")
public class PasswordInfoController {

	@Autowired
	private PasswordInfoService passwordService;

	@Autowired
	private Utilities utilities;

	@GetMapping("/{id}")
	public ResponseEntity<PasswordInfo> findInfoById(
			@PathVariable Long id, 
			@RequestHeader Map<String, String> headers) {

		var passwordInfo = passwordService.findPasswordInfoById(id);
		if (passwordInfo == null) {
			throw new PasswordInfoNotFoundException(
					"password info with id " + id + " not found.");
		}
		
		if (!utilities.canAcessPasswordResource(
				passwordInfo.getUsername(), 
				headers.get("username"))) {

			throw new NotAuthorizedPasswordInfoException(
					"You don't have permission to access this password info with id: " + id);
		}

		return ResponseEntity.ok(passwordInfo);

	}
	
	@GetMapping
	public ResponseEntity<List<PasswordInfo>> findInfoByParams(
			@RequestParam String _name,
			@RequestHeader Map<String, String> headers) {
		
		var username = headers.get("username");
		
		var pg = passwordService.findByNameContainingAndUsername(_name, username);
		
		if (pg == null) {
			// Should I return an empty List or a response without body?
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pg);
		
	}
	
	
}
