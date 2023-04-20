package com.zshop.helper.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import com.zshop.helper.config.ShowBroadcastConfig;
import com.zshop.helper.exception.business.BusinessException;
import com.zshop.helper.utils.enums.ElkStatusSyncType;
import com.zshop.helper.utils.request.ElkShowBuilder;
import com.zshop.helper.utils.request.ElkStatusSyncRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElkBroadcastUtils {

	public void getBroadcastELkResponse(Map<ElkStatusSyncType, ElkShowBuilder> elkStatusMap,
			ShowBroadcastConfig showBroadcastConfig) throws BusinessException, IOException {
		if (!MapUtils.isEmpty(elkStatusMap)) {

			String url = new StringBuilder(showBroadcastConfig.getBaseUrl()).append(showBroadcastConfig.getSyncStatus())
					.toString();
			String response = ServiceUtils.put(url,
					GsonUtils.getJson(ElkStatusSyncRequest.builder().elkStatusMap(elkStatusMap).build()));

			if (ServiceUtils.isSuccessResponse(response)) {
				log.info("sync and updated successfully");
			}

		}
	}
}
