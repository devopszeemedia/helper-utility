package com.zshop.helper.utils.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmailServiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;

	private String messageId;
	
}