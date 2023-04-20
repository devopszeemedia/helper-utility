package com.zshop.helper.exception.business;

import com.zshop.helper.exception.BaseException;

public class NotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}
	
	public NotFoundException(Integer statusCode, String errorMessage, Throwable cause) {
		super(statusCode, errorMessage, cause);
	}

}
