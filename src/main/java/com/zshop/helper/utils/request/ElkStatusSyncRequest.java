package com.zshop.helper.utils.request;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zshop.helper.utils.enums.ElkStatusSyncType;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElkStatusSyncRequest {

	Map<ElkStatusSyncType, ElkShowBuilder> elkStatusMap;
	
	private String elkStatusSyncType;
	private Set<Long> showIds;
	private Set<Long> poductIds;
	private Set<Long> storeIds;

}
