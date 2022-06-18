package br.dev.luisgustavosales.generatepassword.exceptionshandler.groupexceptions;

public class GroupIsUsedOnPasswordInfoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public GroupIsUsedOnPasswordInfoException(String message) {
		super(message);
	}
}
