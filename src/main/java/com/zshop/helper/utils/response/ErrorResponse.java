package com.zshop.helper.utils.response;

import java.io.Serializable;


public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public ErrorResponse setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ErrorResponse setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

}
