package com.zshop.helper.utils;

import java.util.Map;

import com.zshop.helper.exception.business.BusinessException;

public interface RedisService {

	void pushDataInCache(Map<String, String> keyValue) throws BusinessException;

	String getDataFromCache(String key) throws BusinessException;

}
