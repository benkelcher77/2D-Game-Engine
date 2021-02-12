package com.ben.engine.ecs;

public class ComponentNotFoundException extends RuntimeException {

	public ComponentNotFoundException() {
		
	}

	public ComponentNotFoundException(String message) {
		super(message);
	}

	public ComponentNotFoundException(Throwable cause) {
		super(cause);
	}

	public ComponentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComponentNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
