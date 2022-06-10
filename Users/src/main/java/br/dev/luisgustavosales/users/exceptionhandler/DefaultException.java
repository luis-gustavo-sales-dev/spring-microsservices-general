package br.dev.luisgustavosales.users.exceptionhandler;

import java.time.LocalDateTime;

public class DefaultException {
	private int status;
	private String title;
	private LocalDateTime dateTime;
	
	public DefaultException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DefaultException(int status, String title, LocalDateTime dateTime) {
		super();
		this.status = status;
		this.title = title;
		this.dateTime = dateTime;
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
