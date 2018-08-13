package br.com.javaspring.cursomc.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.javaspring.cursomc.services.exception.DataIntegrityException;
import br.com.javaspring.cursomc.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
		StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandartError> dataIntegrity(DataIntegrityException exception, HttpServletRequest request){
		StandartError error = new StandartError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
		
		for (FieldError fe: exception.getBindingResult().getFieldErrors()) {
			error.addError(fe.getField(), fe.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
}
