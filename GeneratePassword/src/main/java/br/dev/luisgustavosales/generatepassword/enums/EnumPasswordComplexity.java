package br.dev.luisgustavosales.generatepassword.enums;

public enum EnumPasswordComplexity {
	UPPERCASE(1, "UPPERCASE"), 
	LOWERCASE(2, "LOWERCASE"), 
	NUMBER(3, "NUMBER"), 
	SIMBOLS(4, "SIMBOLS");
	
	private int cod;
	private String descricao;
	
	private EnumPasswordComplexity(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	
	
}
