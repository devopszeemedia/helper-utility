package com.zshop.helper.utils.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class EmailServiceRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<String> toAddress;
	
	private String subject;
	
	private String mailContent;

}

