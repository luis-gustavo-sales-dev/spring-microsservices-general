package br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions;

public class PasswordInfoAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PasswordInfoAlreadyExistsException(String message) {
		super(message);
	}
}
