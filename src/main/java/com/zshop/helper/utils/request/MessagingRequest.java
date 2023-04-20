package com.zshop.helper.utils.request;

import java.util.Map;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessagingRequest {

	private String fileName;
	@Pattern(regexp = "\\d{10}", message = "{Mobile must be 10 digits long}")
	private String mobile;
	@Email
	private String email;
	private String subject;
	private String whatsappTemplateName;
	private String smsHeader;

	Map<String, String> dataContentEmail;
	Map<String, String> dataContentMobile;
	Map<String, String> dataContentWhatsapp;

}
