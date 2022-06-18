package br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions;

public class LoginAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LoginAlreadyExistsException(String message) {
		super(message);
	}
}
