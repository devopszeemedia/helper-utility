package com.zshop.helper.model;

/**
 * Kafka Topics
 * 
 * @author shirshsinha
 *
 */
public interface Topics {

	final String SELLER_OTP = "seller_otp";
	final String SELER_OTP_FAILOVER = "seller_otp_failover";
	final String MESSAGING = "messaging";
	final String MESSAGING_FAILOVER = "messaging_failover";
	
	final String IMAGE_RESIZE = "image_resize";
	final String IMAGE_RESIZE_FAILOVER = "image_resize_failover";
}
