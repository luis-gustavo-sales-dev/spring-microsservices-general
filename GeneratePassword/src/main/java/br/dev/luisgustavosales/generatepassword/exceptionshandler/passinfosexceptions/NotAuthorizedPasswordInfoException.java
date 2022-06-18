package br.dev.luisgustavosales.generatepassword.exceptionshandler.passinfosexceptions;

public class NotAuthorizedPasswordInfoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedPasswordInfoException(String message) {
		super(message);
	}
}
