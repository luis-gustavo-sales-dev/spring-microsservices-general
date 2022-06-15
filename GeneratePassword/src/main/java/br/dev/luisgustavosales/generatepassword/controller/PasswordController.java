package br.dev.luisgustavosales.generatepassword.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.services.PasswordService;

@RestController("passwords")
public class PasswordController {
	
	@Autowired
	private PasswordService passwordService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PasswordInfo> findById(@PathVariable Long id) {
		var passwordInfo = passwordService.findPasswordInfoById(id);
		if (passwordInfo != null) {
			return ResponseEntity.ok(passwordInfo);
		}
		return ResponseEntity.notFound().build();
	}
}