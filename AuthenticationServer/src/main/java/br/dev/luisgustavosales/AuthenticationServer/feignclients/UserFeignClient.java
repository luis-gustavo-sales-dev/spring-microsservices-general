package br.dev.luisgustavosales.AuthenticationServer.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.dev.luisgustavosales.AuthenticationServer.entities.User;

@Component
@FeignClient( name = "users", path = "/usersforauthentication")
public interface UserFeignClient {
	
	//@GetMapping
	@GetMapping(value = "/{email}")
	ResponseEntity<User> findUserByEmail(@PathVariable String email);

}
