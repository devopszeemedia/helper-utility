package com.zshop.helper.utils.request;

import java.util.Map;

import lombok.Data;

@Data
public class WhatsappRequest {

	private String recipientName;
	private String recipientContactNo;
	private String templateName;
	private Map<String, String> templateBody;
}
