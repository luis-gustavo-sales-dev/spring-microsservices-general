package br.dev.luisgustavosales.generatepassword.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.dev.luisgustavosales.generatepassword.config.Utilities;
import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.services.PasswordService;

@RestController("passwords")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private Utilities utilities;

	@GetMapping("/{id}")
	public ResponseEntity<PasswordInfo> findById(@PathVariable Long id, @RequestHeader Map<String, String> headers) {

		/*
		 * headers.forEach((key, value) -> {
		 * System.out.println(String.format("Header '%s' = %s", key, value)); });
		 */

		var passwordInfo = passwordService.findPasswordInfoById(id);
		if (passwordInfo == null) {
			// System.out.println("PasswordController: " + passwordInfo);
			return ResponseEntity.notFound().build();
		}
		
		/*System.out.println(
				utilities.canAcessPasswordResource(
					passwordInfo.getUsername(), 
					headers.get("username")));
		 */
		
		if (!utilities.canAcessPasswordResource(
				passwordInfo.getUsername(), 
				headers.get("username"))) {

			/*
			 * System.out.println("PasswordController: passwordInfo.Username" +
			 * passwordInfo.getUsername() + " Username: " + headers.get("username"));
			 */

			// Must throws an exception
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(passwordInfo);

	}
}
