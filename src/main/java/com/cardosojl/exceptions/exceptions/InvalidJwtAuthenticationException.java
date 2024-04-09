package com.cardosojl.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{

	private static final long serialVersionUID = 871096056366207048L;
	
	public InvalidJwtAuthenticationException(String ex) {
		super(ex);
	}
	
	

}
