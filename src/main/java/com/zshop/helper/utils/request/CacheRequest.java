package com.zshop.helper.utils.request;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CacheRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cacheCode;

	private Map<String, String> data;

	private String key;

	private Set<String> keys;

	private Integer expiry;

	private String value;

}
