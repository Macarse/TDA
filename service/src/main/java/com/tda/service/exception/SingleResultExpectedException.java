package com.tda.service.exception;

@SuppressWarnings("serial")
public class SingleResultExpectedException extends Exception {
	public SingleResultExpectedException() {
		super();
	}

	public SingleResultExpectedException(String message) {
		super(message);
	}

	public SingleResultExpectedException(Throwable cause) {
		super(cause);
	}

	public SingleResultExpectedException(String message, Throwable cause) {
		super(message, cause);
	}
}
