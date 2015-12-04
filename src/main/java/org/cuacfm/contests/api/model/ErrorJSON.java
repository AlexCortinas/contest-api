package org.cuacfm.contests.api.model;

public class ErrorJSON {
	private final String message;

	public ErrorJSON(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
