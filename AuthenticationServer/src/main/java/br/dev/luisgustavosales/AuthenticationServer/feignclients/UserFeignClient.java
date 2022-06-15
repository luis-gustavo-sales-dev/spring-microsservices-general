package br.dev.luisgustavosales.AuthenticationServer.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.dev.luisgustavosales.AuthenticationServer.dto.ResponseUserDTO;
import br.dev.luisgustavosales.AuthenticationServer.entities.User;

@Component
@FeignClient( name = "users", path = "/users")
public interface UserFeignClient {
	
	@GetMapping
	// @GetMapping(value = "/search")
	ResponseEntity<ResponseUserDTO> findByEmail(@RequestParam String email);

}
