package com.zshop.helper.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * common time units enum, using in show deferred cron configuration
 *
 */
public enum TimeUnitsEnum {

	DAY("DAY"), MINUTE("MINUTE");

	private String source;

	TimeUnitsEnum(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	private static final Map<String, TimeUnitsEnum> timeUnitsLookup = new HashMap<>();

	static {
		for (TimeUnitsEnum timeUnitsEnum : TimeUnitsEnum.values()) {
			timeUnitsLookup.put(timeUnitsEnum.getSource(), timeUnitsEnum);
		}
	}

	public static TimeUnitsEnum getTimeUnitsEnumBySource(String timeUnitsEnum) {
		return timeUnitsLookup.get(timeUnitsEnum);
	}

}
