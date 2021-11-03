package com.OsSystem.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FildMessage> errors = new ArrayList<>();

		
	public ValidationError() {
		super();
	}
	
	public ValidationError(Long timestam, Integer status, String error) {
		super(timestam, status, error);
	}

	public List<FildMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fildName, String message) {
		this.errors.add(new FildMessage(fildName, message));
	}

}
