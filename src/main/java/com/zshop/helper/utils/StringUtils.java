package com.zshop.helper.utils;

import java.security.SecureRandom;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtils {

	/**
	 * The empty String <code>""</code>.
	 * 
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	private static final int RANDOM_STRING_DEFAULT_LENGTH = 10;

	private static final Random random = new Random();

	public static String generateRandom(int length) {
		try {
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
			SecureRandom rand = SecureRandom.getInstanceStrong();
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++)
				sb.append(chars.charAt(rand.nextInt(chars.length())));
			return sb.toString();
		} catch (Exception e) {
			log.error("Error occured while generating random string : {}", e);
			return EMPTY;
		}
	}

	public static Long generateLongRandom(int length) {
		try {
			String chars = "123456789";
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++)
				sb.append(chars.charAt(random.nextInt(chars.length())));
			return Long.parseLong(sb.toString());
		} catch (Exception e) {
			log.error("Error occured while generating random long : {}", e);
			return null;
		}
	}
}
