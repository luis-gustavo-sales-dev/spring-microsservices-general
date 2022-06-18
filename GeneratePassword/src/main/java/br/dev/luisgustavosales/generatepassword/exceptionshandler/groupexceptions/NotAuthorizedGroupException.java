package br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions;

public class NotAuthorizedGroupException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedGroupException(String message) {
		super(message);
	}
}
