package br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions;

public class LoginNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LoginNotFoundException(String message) {
		super(message);
	}
}
