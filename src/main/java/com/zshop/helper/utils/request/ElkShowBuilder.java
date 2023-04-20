package com.zshop.helper.utils.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElkShowBuilder {

	private Set<Long> showIds;
	private String productId;
	private Long storeId;
	
	/**
	 * property to keep track of system-show elk flow, where sms/email confirmation
	 * needs to be skipped.
	 */
	@Builder.Default
	private Boolean isAdminFlow = false;

}
