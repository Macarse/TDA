package com.tda.service.exception;

@SuppressWarnings("serial")
public class NoDataFoundException extends Exception {
	public NoDataFoundException() {
		super();
	}

	public NoDataFoundException(String message) {
		super(message);
	}

	public NoDataFoundException(Throwable cause) {
		super(cause);
	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
