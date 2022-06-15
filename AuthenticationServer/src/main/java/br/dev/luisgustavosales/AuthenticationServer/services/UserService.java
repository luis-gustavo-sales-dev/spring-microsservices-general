package br.dev.luisgustavosales.AuthenticationServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.AuthenticationServer.dto.ResponseUserDTO;
import br.dev.luisgustavosales.AuthenticationServer.entities.User;
import br.dev.luisgustavosales.AuthenticationServer.feignclients.UserFeignClient;
import lombok.extern.java.Log;

@Service
@Log
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public ResponseUserDTO findByEmail(String email) {
		ResponseUserDTO user = userFeignClient.findUserDtoByEmail(email).getBody();
		
		if (user == null) {
			log.info("User not found: " + email);
			return null;
		}
		log.info("User was found: " + email);
		return user;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findUserByEmail(username).getBody();
		if (user == null) {
			System.out.println("Email not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		System.out.println("Email found: " + username);
		return user;
	}
	
}
