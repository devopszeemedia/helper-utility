package com.zshop.helper.utils.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * common status enum used for show status
 *
 */
public enum ShowStatusEnum {

	DRAFTED("DRAFTED"), PENDING_APPROVAL("PENDING_APPROVAL"), APPROVED("APPROVED"), REJECTED("REJECTED"),
	SCHEDULED("SCHEDULED"), PROD_COLL("PROD_COLL"), THUMBNAIL("THUMBNAIL"), VIDEOS("VIDEOS"), SLOT_ALLOT("SLOT_ALLOT"),
	STARTED("STARTED"), ONAIR("ONAIR"), AIRED("AIRED"), UPCOMING("UPCOMING"), INACTIVE("INACTIVE"),

	DEFER_DRAFTED("DEFER_DRAFTED"), DEFER_PENDING_APPROVAL("DEFER_PENDING_APPROVAL"),
	DEFER_SCHEDULED("DEFER_SCHEDULED"), DEFER_REJECTED("DEFER_REJECTED"), DEFER_PRE_STARTED("DEFER_PRE_STARTED"),
	DEFER_PRE_ONAIR("DEFER_PRE_ONAIR"), DEFER_STARTED("DEFER_STARTED"), DEFER_ONAIR("DEFER_ONAIR");

	private String source;

	ShowStatusEnum(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	private static final Map<String, ShowStatusEnum> showStatusLookup = new HashMap<>();

	static {
		for (ShowStatusEnum showStatusEnum : ShowStatusEnum.values()) {
			showStatusLookup.put(showStatusEnum.getSource(), showStatusEnum);
		}
	}

	public static ShowStatusEnum getShowStatusEnumBySource(String showStatusEnum) {
		return showStatusLookup.get(showStatusEnum);
	}

	/*
	 * List of status after which show editing is not allowed.
	 */
	public static List<String> getThresholdStatus() {

		return Arrays.asList(STARTED.getSource(), AIRED.getSource(), ONAIR.getSource());
	}

}
