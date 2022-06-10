package br.dev.luisgustavosales.users.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.users.entities.User;
import br.dev.luisgustavosales.users.exceptionhandler.exceptions.UserAlreadyExistsException;
import br.dev.luisgustavosales.users.exceptionhandler.exceptions.UserNotFoundException;
import br.dev.luisgustavosales.users.services.UserService;
import lombok.extern.java.Log;

@RestController
@RequestMapping("users")
@Log
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		log.info("Find User by Id: " + id);
		User user = userService.findUserById(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			throw new UserNotFoundException("User not found: " + id);
			// return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
		log.info("Find User by Email: " + email);
		User user = userService.findUserByEmail(email);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			throw new UserNotFoundException("User not found: " + email);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		log.info("Create new user: " + user.toString());
		
		User userToCreate = userService.findUserByEmail(user.getEmail());
		if (userToCreate != null) {
			throw new UserAlreadyExistsException("User already exists: " + user.getEmail());
		} else {
			User newUser = userService.create(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		}
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(
			@Valid @RequestBody User user, 
			@PathVariable Long id) {
		
		log.info("Updade user: " + user);
		
		User userToUpdated = userService.findUserById(id);
		if (userToUpdated != null) {
			user.setId(id);
			User userUpdated = userService.update(id, user);
			return ResponseEntity.ok(userUpdated);
		} else {
			throw new UserNotFoundException("User not found: " + id);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		
		log.info("Delete user: " + id);
		
		User userToDelete = userService.findUserById(id);
		if (userToDelete != null) {
			userService.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			throw new UserNotFoundException("User not found: " + id);
		}
		
	}
}
