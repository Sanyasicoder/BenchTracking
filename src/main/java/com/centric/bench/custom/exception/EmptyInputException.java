package com.centric.bench.custom.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
public class EmptyInputException extends RuntimeException {
	
	
	
	
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	public EmptyInputException(String errorCode, String errorMessage) {		
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public EmptyInputException() {
		
	};
	
	
	

}
