package com.zshop.helper.utils.enums;

import java.util.Arrays;

public enum ElkStatusSyncEvents {

	INSERT("insert"), UPDATE("update"), DELETE("delete"), NONE("none");

	private String syncType;

	ElkStatusSyncEvents(String syncType) {
		this.syncType = syncType;
	}

	public String getSyncType() {
		return syncType;
	}

	public static ElkStatusSyncEvents findByCode(String type) {

		return Arrays.stream(ElkStatusSyncEvents.values()).filter(e -> e.getSyncType().equalsIgnoreCase(type)).findFirst()
				.orElse(ElkStatusSyncEvents.NONE);

	}

}