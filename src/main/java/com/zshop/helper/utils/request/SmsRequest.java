package com.zshop.helper.utils.request;

import java.io.Serializable;
import java.util.Map;

import com.zshop.helper.utils.enums.CommunicationTypeEnum;

import lombok.Data;

@Data
public class SmsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> dataValues;

	private CommunicationTypeEnum communicationType;

	private String templateFileName;

	private String mobileNo;

	private String smsHeader;


}
