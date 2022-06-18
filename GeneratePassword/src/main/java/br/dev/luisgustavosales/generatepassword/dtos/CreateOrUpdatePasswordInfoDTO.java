package br.dev.luisgustavosales.generatepassword.dtos;

import java.util.Objects;

public class CreateOrUpdatePasswordInfoDTO {
	
	private String name;
	private String domain;
	private int size;
	private Long login;
	private Long group;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getLogin() {
		return login;
	}

	public void setLogin(Long login) {
		this.login = login;
	}

	public Long getGroup() {
		return group;
	}

	public void setGroup(Long group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateOrUpdatePasswordInfoDTO other = (CreateOrUpdatePasswordInfoDTO) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "CreateOrUpdatePasswordInfoDTO [name=" + name + ", domain=" + domain + ", size=" + size + ", login="
				+ login + ", group=" + group + "]";
	}
	
	
	
	

	
	
	
}
