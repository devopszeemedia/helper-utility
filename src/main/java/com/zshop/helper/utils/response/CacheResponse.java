package com.zshop.helper.utils.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CacheResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> data;
	private List<String> values;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> getData() {
		return data;
	}

	private String status;
	
	public Map<String, Object> getCacheData() {
		if (data == null) {
			data = new HashMap<>();
		}
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public List<String> getValues() {
		return values;
	}
	
	public void setValues(List<String> values) {
		this.values = values;
	}


}
