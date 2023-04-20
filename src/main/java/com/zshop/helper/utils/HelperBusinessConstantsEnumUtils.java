package com.zshop.helper.utils;

import static com.zshop.helper.utils.ConversionUtils.getSafeLong;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * 
 * @author shirshsinha
 *
 */
@Component
public class HelperBusinessConstantsEnumUtils {

	private static String mobile = "mobile";
	private static String email = "email";

	public enum EmailContent {

		MOBILE(mobile), EMAIL(email), EMAIL_ID("emailId"), SELLER_NAME("sellerName");

		private String source;

		EmailContent(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}

		public static boolean isValidSourceType(String type) {

			return Stream.of(EmailContent.values()).anyMatch(e -> e.getSource().equalsIgnoreCase(type));
		}
	}

	public enum ShowTypeEnum {

		LIVE("live"), REC("rec");

		private String source;

		ShowTypeEnum(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

	public enum UserConstants {

		USER_NOT_EXISTS("No user exists with given userId"), INVALID_CREDENTIALS("Username/password not correct"),
		LOGOUT_SUCCESS("Logout Successfully"), PASSWORD_CHANGE_SUCCESSFULLY("Password change successfully"),
		FORGET_PASSWORD_TEXT("Otp has been sent to registered emailId/mobile."),
		FORGET_PASSWORD("Request for forget password."), PASSWORD_CHANGE_NOTIFY_SUBJECT("Password update notification");

		private String source;

		UserConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

	public enum ErrorConstants {

		TYPE_MANDATORY("type is required and must be mobile/email."), SLOT_DATE_OUT_OF_RANGE("Not a valid slot date.");

		private String source;

		ErrorConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}

	}

	public enum TokenConstants {

		SELLER_ID("sellerId"), MOBILE(mobile), EMAIL(email), SELLER_VMT("sellerVmt"), USERNAME("username"),
		SELLER_NAME("sellerName"), PASSWORD("password"), REFRESH_TOKEN_NOT_FOUND("No refresh token found"),
		GRANT_TYPE("grant_type"), ACCESS_TOKEN("access_token"), BEARER("Bearer"), AUTHORIZATION("Authorization"),
		REFRESH_TOKEN("refresh_token"), CLIENT("client"), SCOPE_READ("read");

		private String source;

		TokenConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}

	}

	public enum KeyValueConstants {

		MOBILE(mobile), EMAIL(email), OTP("otp"), FILENAME("filename"), SUBJECT("subject");

		private String key;

		KeyValueConstants(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}

	public enum ApprovalConstants {

		APPROVAL_SYSTEM("APPROVAL_SYSTEM"), STATUS_TRANSITION("STATUS_TRANSITION");

		private String source;

		ApprovalConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

	public enum OTPConstants {

		OTP_SERVICE("OTP_SERVICE"), COMMUNICATION("COMMUNICATION");

		private String source;

		OTPConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

	public enum ShowElkOperationConstants {

		CREATE("CREATE"), DELETE("DELETE"), NONE("NONE"), UPDATE("UPDATE");

		private String source;

		ShowElkOperationConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

	public enum SellerConstants {

		SELLER_NOT_EXISTS("Seller not found for given seller id"), USER_BLOCKED("The user is blocked"),
		SELLER_EXISTS_FLAG("Y"), SELLER_APP("Seller App") ,SSO_USER_NOT_EXISTS("User not found in SSO"),
		SSO_USER_NOT_INACTIVE_OR_BLOCKED("SSO user is either Inactive or Blocked");

		private String source;

		SellerConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}

	}

	public enum ShowDeferred {

		DEFERRED_SHOW_CRON("Deferred Show Cron");

		private String source;

		ShowDeferred(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}

	}

	public enum PromotionsConstants {

		SHOW_BANNER("12"), SHOW_GROUP_TEXT("ShowGroup"), SHOW("13"), POST_GROUP("18");

		private String source;

		PromotionsConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}

		public static Long getShowBannerId() {
			return getSafeLong(PromotionsConstants.SHOW_BANNER.getSource());
		}

	}

	public enum SeoConstants {

		URL_DELIMITER("/"), HYPHEN_KEYWORD("-"), SPACE_KEYWORD(" "), EXTRA_CHAR_REGEX_EXP("[^a-zA-Z0-9]"),
		SPECIAL_CHARACTERS_KEYWORD("[\'\"]"), HYPEN_FIRST_LAST_OCCURENCE_REGEX_EXP("^-|-$"), HYPEN_REGEX_EXP("-{2,}"),
		SEO_REDIS_KEY("SEO_INVALID_KEYWORDS");

		private String source;

		SeoConstants(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

	public enum ShowConstraint {

		SHOW_CONFIG_CONSTRAINT("SHOW_CONFIG_CONSTRAINT");

		private String source;

		ShowConstraint(String source) {
			this.source = source;
		}

		public String getSource() {
			return source;
		}
	}

}
