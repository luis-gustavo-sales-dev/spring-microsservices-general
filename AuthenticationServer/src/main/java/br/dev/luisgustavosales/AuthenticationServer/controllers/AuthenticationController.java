package br.dev.luisgustavosales.AuthenticationServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.AuthenticationServer.dto.ResponseUserDTO;
import br.dev.luisgustavosales.AuthenticationServer.services.UserService;
import lombok.extern.java.Log;

@RestController
@Log
@RequestMapping( value = "/micro-users")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	// Testing find user from feignclient
	
	/*@GetMapping
	public ResponseEntity<ResponseUserDTO> findByEmail(@RequestParam String email) {
		System.out.println("Searching for email: " + email);
		log.info("Searching for email: " + email);
		ResponseUserDTO u = userService.findByEmail(email);
		if (u == null) {
			// Should throws an UserNotFoundException here
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(u);
	}*/
}
