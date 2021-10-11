package com.endlos.admin.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFound extends RuntimeException {
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

