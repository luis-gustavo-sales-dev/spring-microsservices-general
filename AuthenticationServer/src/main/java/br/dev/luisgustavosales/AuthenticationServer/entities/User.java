package br.dev.luisgustavosales.AuthenticationServer.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class User {
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	private String email;
	private String password;
	
	
}
