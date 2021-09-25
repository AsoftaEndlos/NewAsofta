package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileStorageException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6954925235187536228L;
	public FileStorageException(String message) 
	{
		super(message);
	
	}
	public FileStorageException(String message, Throwable cse) 
	{
		super(message,cse);
	
	}
}
