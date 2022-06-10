package br.dev.luisgustavosales.users.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		var defaultExeception = new DefaultException();
		defaultExeception.setTitle("Fields are not filled correctly.");
		defaultExeception.setStatus(status.value());
		defaultExeception.setDateTime(LocalDateTime.now());
		
		// Mapping error fields to DefaultException FieldErrors
		
		var fieldsErrors = new ArrayList<DefaultException.FieldsErrors>();
		
		for ( ObjectError error: ex.getBindingResult().getAllErrors()) {
			fieldsErrors.add(new DefaultException.FieldsErrors(
						((FieldError)error).getField(),
						error.getDefaultMessage()
					));
		}
		
		defaultExeception.setFieldsErrors(fieldsErrors);
	
		return super.handleExceptionInternal(ex, defaultExeception, headers, status, request);
		
	}
	
}
