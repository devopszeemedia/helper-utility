package com.zshop.helper.utils;

import java.util.Arrays;

public enum StatusCodeEnum {

	SUCCESS_CODE(2000, "Request Successful"), PARAMS_MISSING_CODE(4000, "Request parameters are missing / in-valid"),
	TOKEN_EXPIRE_CODE(4001, "Token has expired"), TOKEN_REQUIRED(4002, "Token is mandatory"),
	TOKEN_INVALID(4003, "Invalid access token"),
	FILE_UPLOAD_ERROR(4005, "Failed to uplaod file.Please try again later."),
	NOT_ALLOWED(4006, "Not allowed for this operation"),
	FULL_AUTHENTICATION_REQUIRED(4004, "Full authentication is required to access this resource"),
	OTP_INCORRECT(4010, "OTP incorrect. Please enter correct OTP code"),
	OTP_GENERATION_FAIL_CODE(4011, "Failed to generate OTP code"), OTP_EXPIRED_CODE(4012, "OTP code has expired"),
	EMAIL_FAILURE(4013, "Email not sent, technical failure occurs"),
	ALREADY_EXIST_CODE(4030, "Requested parameters already exists"),
	STORE_ATTACH_SHOW_WARNING(4034, "Show is attached with given store. Do you really want to update?"),
	STORE_ATTACH_START_SHOW_ERROR(4035, "Show is already running/started with given store. Please try again later"),
	PRIVATE_PROFILE(4037, "This Account is private. You can not follow the user."),
	RESTRICTED_UNFOLLOW_PROFILE(4038, "This Account is Private Follow to see their photos and videos."),
	NOT_EXIST_CODE(4031, "No data found for given criteria."), UNAUTHORIZED(4032, "Not authorzed for this request."),
	BAD_CREDENTIALS(4033, "Bad credentials provided."), DEVICE_ALREDY_REGISTER(4036, "Device already registered."),
	EXCEPTION_CODE(5000, "Some server exception occured."), ILLEGAL_STATUS_CODE(-1, "Illegal status code");

	private Integer code;
	private String message;

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private StatusCodeEnum(Integer code, String status) {
		this.code = code;
		this.message = status;
	}

	public static StatusCodeEnum findByCode(Integer code) {

		return Arrays.stream(StatusCodeEnum.values()).filter(e -> e.getCode().equals(code)).findFirst()
				.orElse(StatusCodeEnum.ILLEGAL_STATUS_CODE);

	}

}
