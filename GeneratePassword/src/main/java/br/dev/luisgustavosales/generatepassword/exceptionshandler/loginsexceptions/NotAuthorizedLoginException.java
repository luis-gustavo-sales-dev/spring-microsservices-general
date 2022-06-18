package br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions;

public class NotAuthorizedLoginException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedLoginException(String message) {
		super(message);
	}
}
