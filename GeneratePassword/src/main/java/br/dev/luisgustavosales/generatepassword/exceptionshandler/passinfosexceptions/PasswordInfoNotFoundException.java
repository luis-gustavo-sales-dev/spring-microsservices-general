package br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions;

public class PasswordInfoNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PasswordInfoNotFoundException(String message) {
		super(message);
	}
}
