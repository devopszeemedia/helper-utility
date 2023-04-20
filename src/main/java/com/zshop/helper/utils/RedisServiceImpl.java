package com.zshop.helper.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zshop.helper.exception.business.BusinessException;
import com.zshop.helper.utils.request.CacheImplementationRequest;
import com.zshop.helper.utils.response.CacheImplementationResponse;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RemoteCache remoteCache;

	private static final String CACHE_CODE_JEDIS = "Jedis";

	private CacheImplementationRequest saveToRedis(Map<String, String> redisKeyValue) {
		CacheImplementationRequest request = new CacheImplementationRequest();
		request.setCacheCode(CACHE_CODE_JEDIS);
		request.setData(redisKeyValue);
		return request;
	}

	private CacheImplementationRequest prepareRequestToGetAspectRatioDataFromCache(String key) {
		CacheImplementationRequest request = new CacheImplementationRequest();
		request.setCacheCode(CACHE_CODE_JEDIS);
		request.setKey(key);
		return request;
	}

	public void pushDataInCache(Map<String, String> redisKeyValue) throws BusinessException {
		remoteCache.pushAspectRatioDataToCache(GsonUtils.getJson(saveToRedis(redisKeyValue)));

	}

	public String getDataFromCache(String key) throws BusinessException {
		CacheImplementationResponse pullAspectRatioDataFromCache = remoteCache
				.pullAspectRatioDataFromCache(GsonUtils.getJson(prepareRequestToGetAspectRatioDataFromCache(key)));
		if (pullAspectRatioDataFromCache != null) {

			return (String) pullAspectRatioDataFromCache.getData().get(key);
		}

		return null;

	}

}
