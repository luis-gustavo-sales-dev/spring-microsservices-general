package br.dev.luisgustavosales.users.dto;

import java.util.HashSet;
import java.util.Set;

import br.dev.luisgustavosales.users.entities.Role;

public class ResponseUserDTO {

	private Long id;
	private String name;
	private String email;
	private Set<Role> roles = new HashSet<>();
	
	
	public ResponseUserDTO() {
		
	}
	
	
	public ResponseUserDTO(Long id, String name, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	


}

