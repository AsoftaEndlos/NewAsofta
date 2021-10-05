package com.endlos.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFound extends  RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5797029012016086390L;

	public FileNotFound(String message) {
        super(message);
    }

    public FileNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

