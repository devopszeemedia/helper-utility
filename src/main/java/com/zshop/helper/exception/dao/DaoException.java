package com.zshop.helper.exception.dao;

import com.zshop.helper.exception.BaseException;

public class DaoException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

}
