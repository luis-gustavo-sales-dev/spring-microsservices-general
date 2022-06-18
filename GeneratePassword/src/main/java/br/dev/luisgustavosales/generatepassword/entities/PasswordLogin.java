package br.dev.luisgustavosales.generatepassword.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table( name = "tb_password_login" )
public class PasswordLogin {
	
	@Id
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank(message = "The username must have a valid value")
	private String username;
	
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
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordLogin other = (PasswordLogin) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "PasswordLogin [id=" + id + ", name=" + name + ", username=" + username + "]";
	}

	
	
	
	
	
	
}
