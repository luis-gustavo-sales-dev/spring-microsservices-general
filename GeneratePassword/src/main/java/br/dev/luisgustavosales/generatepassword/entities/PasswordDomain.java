package br.dev.luisgustavosales.generatepassword.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PasswordDomain {
	
	@Id
	private Long id;
	private Long name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getName() {
		return name;
	}
	public void setName(Long name) {
		this.name = name;
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
		PasswordDomain other = (PasswordDomain) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "PasswordDomain [id=" + id + ", name=" + name + "]";
	}
	
	
}
