package br.dev.luisgustavosales.users.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DefaultException {
	private int status;
	private String title;
	private LocalDateTime dateTime;
	private List<FieldsErrors> fieldsErrors;
	
	public static class FieldsErrors {
		
		private String name;
		private String message;
		
		public FieldsErrors(String name, String message) {
			super();
			this.name = name;
			this.message = message;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
	}
	
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
	
	
	
	
	public List<FieldsErrors> getFieldsErrors() {
		return fieldsErrors;
	}

	public void setFieldsErrors(List<FieldsErrors> fieldsErrors) {
		this.fieldsErrors = fieldsErrors;
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
