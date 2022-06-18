package br.dev.luisgustavosales.generatepassword.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.generatepassword.config.Utilities;
import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
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
			return ResponseEntity.notFound().build();
		}
		
		if (!utilities.canAcessPasswordResource(
				passwordInfo.getUsername(), 
				headers.get("username"))) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(passwordInfo);

	}
}