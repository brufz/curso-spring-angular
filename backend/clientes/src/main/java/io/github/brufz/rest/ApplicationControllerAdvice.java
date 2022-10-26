package io.github.brufz.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.github.brufz.rest.exception.APIErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public APIErrors handleValidationErrors( MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages =  bindingResult.getAllErrors().stream().map(object -> object.getDefaultMessage())
		.collect(Collectors.toList());
		
		return new APIErrors(messages);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String errorMessage = ex.getMessage();
		HttpStatus statusCode = ex.getStatus();
		APIErrors apiErrors = new APIErrors(errorMessage);
		
		return new ResponseEntity(apiErrors, statusCode);
		
	}

}
