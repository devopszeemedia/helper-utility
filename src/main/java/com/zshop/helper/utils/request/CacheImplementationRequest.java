package com.zshop.helper.utils.request;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CacheImplementationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cacheCode;

	private Map<String, String> data;

	private String key;

	private Set<String> keys;

	private Integer expiry;

	private String value;

	public String getCacheCode() {
		return cacheCode;
	}

	public void setCacheCode(String cacheCode) {
		this.cacheCode = cacheCode;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Set<String> getKeys() {
		return keys;
	}

	public void setKeys(Set<String> keys) {
		this.keys = keys;
	}

	public Integer getExpiry() {
		return expiry;
	}

	public void setExpiry(Integer expiry) {
		this.expiry = expiry;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
