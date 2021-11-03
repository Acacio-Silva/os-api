package com.OsSystem.resources.exceptions;

import java.io.Serializable;

public class FildMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;
	public FildMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FildMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fildName) {
		this.fieldName = fildName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
