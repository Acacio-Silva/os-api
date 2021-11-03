package com.OsSystem.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long timestam;
	private Integer status;
	private String error;

	public StandardError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StandardError(Long timestam, Integer status, String error) {
		super();
		this.timestam = timestam;
		this.status = status;
		this.error = error;
	}

	public Long getTimestam() {
		return timestam;
	}

	public void setTimestam(Long timestam) {
		this.timestam = timestam;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
