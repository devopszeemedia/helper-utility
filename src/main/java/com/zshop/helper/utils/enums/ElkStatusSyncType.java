package com.zshop.helper.utils.enums;

import java.util.HashMap;
import java.util.Map;

import com.zshop.helper.utils.HelperBusinessConstantsEnumUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ElkStatusSyncType {

	ELK_PENDING_APPROVAL("elk_pending_approved",
			HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.DELETE.getSource()),
	ELK_APPROVED("elk_approved", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.UPDATE.getSource()),
	ELK_REJECTED("elk_rejected", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.DELETE.getSource()),
	ELK_INACTIVE("elk_inactive", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.DELETE.getSource()),
	ELK_DEFERRED("elk_deferred", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.DELETE.getSource()),
	ELK_HARVEST("elk_harvest", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.UPDATE.getSource()),
	ELK_DB_LOGS("elk_db_logs", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.NONE.getSource()),
	DEFALUT("", ""),
	ELK_DRAFTED("elk_drafted", HelperBusinessConstantsEnumUtils.ShowElkOperationConstants.DELETE.getSource());

	private String syncType;
	private String eventType;

	private static final Map<String, ElkStatusSyncEvents> showStatusLookup = new HashMap<>();

	static {
		for (ElkStatusSyncType showStatusEnum : ElkStatusSyncType.values()) {

			showStatusLookup.put(showStatusEnum.getSyncType(),
					ElkStatusSyncEvents.findByCode(showStatusEnum.getSyncType()));
		}
	}

	public static Map<String, ElkStatusSyncEvents> getShowstatuslookup() {
		return showStatusLookup;
	}

}
