package br.dev.luisgustavosales.generatepassword.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.dev.luisgustavosales.generatepassword.dtos.CreateOrUpdatePasswordInfoDTO;
import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions.GroupNotFoundException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions.LoginNotFoundException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions.NotAuthorizedPasswordInfoException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions.PasswordInfoAlreadyExistsException;
import br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions.PasswordInfoNotFoundException;
import br.dev.luisgustavosales.generatepassword.services.PasswordGroupService;
import br.dev.luisgustavosales.generatepassword.services.PasswordInfoService;
import br.dev.luisgustavosales.generatepassword.services.PasswordLoginService;

@RestController
@RequestMapping(value = "/passwords")
public class PasswordInfoController {

	@Autowired
	private PasswordInfoService passwordInfoService;
	
	@Autowired
	private PasswordLoginService passwordLoginService;
	
	@Autowired
	private PasswordGroupService PasswordGroupService; 

	@Autowired
	private Utilities utilities;

	@GetMapping("/{id}")
	public ResponseEntity<PasswordInfo> findInfoById(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers) {

		var passwordInfo = passwordInfoService.findPasswordInfoById(id);
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

		var pg = passwordInfoService.findByNameContainingAndUsername(_name, username);

		if (pg == null) {
			// Should I return an empty List or a response without body?
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pg);

	}

	@PostMapping
	public ResponseEntity<PasswordInfo> createInfo(
			@RequestBody CreateOrUpdatePasswordInfoDTO createOrUpdatePasswordInfoDTO,
			@RequestHeader Map<String, String> headers) {

		// Find by if exists an password info with the same name

		var username = headers.get("username");

		var pi = passwordInfoService.findByNameAndUsername(
				createOrUpdatePasswordInfoDTO.getName(),
				username);
		
		if (pi != null) {
			throw new PasswordInfoAlreadyExistsException("Password Info name " + 
					createOrUpdatePasswordInfoDTO.getName() + " already exists!");
		}
		
		var passwordInfo = new PasswordInfo();
		
		BeanUtils.copyProperties(createOrUpdatePasswordInfoDTO, passwordInfo, "login", "group");
		
		// Find by login id and verify if the user has access
		
		var pl = passwordLoginService.findByIdAndUsername(
				createOrUpdatePasswordInfoDTO.getLogin(),
				username);
		
		if (pl == null) {
			throw new LoginNotFoundException("Login id " + 
					createOrUpdatePasswordInfoDTO.getLogin()
					+ " was not found or you not have permission to use it.");
		}
		
		passwordInfo.setLogin(pl);
		
		// Find by group id and verify if the user has access
		
		var pg = PasswordGroupService.findByIdAndUsername(
				createOrUpdatePasswordInfoDTO.getGroup(),
				username);
		
		if (pg == null) {
			throw new GroupNotFoundException("Group id " + 
					createOrUpdatePasswordInfoDTO.getGroup()
					+ " was not found or you not have permission to use it.");
		}
		
		passwordInfo.setGroup(pg);
		
		passwordInfo.setUsername(username);
		
		var passwordInfoCreated = passwordInfoService.create(passwordInfo);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(passwordInfoCreated);
				

	}

	
	@PutMapping("/{id}")
	public ResponseEntity<PasswordInfo> updateInfo(
			@RequestBody CreateOrUpdatePasswordInfoDTO createOrUpdatePasswordInfoDTO,
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers) {

		// Find if the user is the owner of the password info

		var username = headers.get("username");
		
		var passwordInfo = passwordInfoService.findPasswordInfoById(id);
		
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
		
		BeanUtils.copyProperties(createOrUpdatePasswordInfoDTO, passwordInfo, "login", "group");

		// Verifify if the user can access the login
		
		var pl = passwordLoginService.findByIdAndUsername(
				createOrUpdatePasswordInfoDTO.getLogin(),
				username);
		
		if (pl == null) {
			throw new LoginNotFoundException("Login id " + 
					createOrUpdatePasswordInfoDTO.getLogin()
					+ " was not found or you not have permission to use it.");
		}
		
		passwordInfo.setLogin(pl);
		
		// Verify if the user can access the group
		
		var pg = PasswordGroupService.findByIdAndUsername(
				createOrUpdatePasswordInfoDTO.getGroup(),
				username);
		
		if (pg == null) {
			throw new GroupNotFoundException("Group id " + 
					createOrUpdatePasswordInfoDTO.getGroup()
					+ " was not found or you not have permission to use it.");
		}
		
		passwordInfo.setGroup(pg);
		
		passwordInfo.setUsername(username);

		var passwordInfoUpdated = passwordInfoService.update(passwordInfo);
		
		return ResponseEntity.ok(passwordInfoUpdated);
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInfo(
			@PathVariable Long id,
			@RequestHeader Map<String, String> headers
			) {
		
		// Find if the user is the owner of the password info

		var username = headers.get("username");
		
		var passwordInfo = passwordInfoService.findPasswordInfoById(id);
		
		if (passwordInfo == null) {
			throw new PasswordInfoNotFoundException(
					"password info with id " + id + " not found.");
		}

		if (!utilities.canAcessPasswordResource(
				passwordInfo.getUsername(), 
				username)) {

			throw new NotAuthorizedPasswordInfoException(
					"You don't have permission to access this password info with id: " + id);
		}
		
		passwordInfoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
