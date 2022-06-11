package br.dev.luisgustavosales.generatepassword.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_password_group")
public class PasswordGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "password_group_id")
	private Long id;
	
	@NotBlank
	private Long userId;
	
	@NotBlank
	private String name;
	

	public PasswordGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordGroup(Long id, @NotBlank Long userId, @NotBlank String name) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordGroup other = (PasswordGroup) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "PasswordGroup [id=" + id + ", userId=" + userId + ", name=" + name + "]";
	}
	
	
}
