package br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions;

public class GroupAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public GroupAlreadyExistsException(String message) {
		super(message);
	}
}
