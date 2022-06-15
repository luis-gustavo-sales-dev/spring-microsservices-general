package br.dev.luisgustavosales.AuthenticationServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.AuthenticationServer.dto.ResponseUserDTO;
import br.dev.luisgustavosales.AuthenticationServer.entities.User;
import br.dev.luisgustavosales.AuthenticationServer.feignclients.UserFeignClient;
import lombok.extern.java.Log;

@Service
@Log
public class UserService {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public ResponseUserDTO findByEmail(String email) {
		ResponseUserDTO user = userFeignClient.findByEmail(email).getBody();
		
		if (user == null) {
			log.info("User not found: " + email);
			return null;
		}
		log.info("User was found: " + email);
		return user;
		
	}
	
}
