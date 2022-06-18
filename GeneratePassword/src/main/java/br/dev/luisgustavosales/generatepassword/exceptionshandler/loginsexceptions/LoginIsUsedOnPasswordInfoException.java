package br.dev.luisgustavosales.generatepassword.exceptionshandler.loginsexceptions;

public class LoginIsUsedOnPasswordInfoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LoginIsUsedOnPasswordInfoException(String message) {
		super(message);
	}
}
