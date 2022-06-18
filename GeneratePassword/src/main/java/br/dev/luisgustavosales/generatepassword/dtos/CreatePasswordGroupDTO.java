package br.dev.luisgustavosales.generatepassword.dtos;

public class CreatePasswordGroupDTO {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PasswordGroupDTO [name=" + name + "]";
	}
	
	
}
