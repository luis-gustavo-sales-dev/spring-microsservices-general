package br.dev.luisgustavosales.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.users.entities.User;
import br.dev.luisgustavosales.users.exceptionhandler.exceptions.UserNotFoundException;
import br.dev.luisgustavosales.users.services.UserService;
import lombok.extern.java.Log;

@RestController
@RequestMapping("usersforauthentication")
@Log
public class UserControllerForAuthentication {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/{email}")
	public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
		log.info("Find User by Email: " + email);
		User user = userService.findUserByEmail(email);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			throw new UserNotFoundException("User not found: " + email);
		}
	}

}
