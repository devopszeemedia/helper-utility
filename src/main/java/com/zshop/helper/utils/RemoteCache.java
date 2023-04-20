package com.zshop.helper.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zshop.helper.config.CacheServiceConfig;
import com.zshop.helper.exception.business.BusinessException;
import com.zshop.helper.utils.response.CacheImplementationResponse;
import com.zshop.helper.utils.response.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RemoteCache {

	@Autowired
	private CacheServiceConfig serviceConfig;


	public void pushAspectRatioDataToCache(String requestString) throws BusinessException {
		String requestUrl = serviceConfig.getBaseUrl() + serviceConfig.getSetDataUrl();
		try {
			log.debug("Calling cache service to set data in cache, request {}", requestString);
			String setCacheDataResponseStr = ServiceUtils.post(requestUrl, requestString);
			if (null != setCacheDataResponseStr) {
				CacheImplementationResponse response = GsonUtils.getObject(setCacheDataResponseStr,
						CacheImplementationResponse.class);
				if (StatusEnum.Failure.equals(response.getStatus())) {
					log.error("Error in cache service - Could not put aspectratio data in cache");
				}
			}
		} catch (Exception ex) {
			log.error("An error occured in AspectRatioRemoteCache.pushAspectRatioDataToCache {}", ex);
			throw new BusinessException("ASPECT_RATIO_ERR", ex.getMessage(), ex.getCause());
		}
	}

	public CacheImplementationResponse pullAspectRatioDataFromCache(String requestString) throws BusinessException {

		String requestUrl = serviceConfig.getBaseUrl() + serviceConfig.getGetDataUrl();
		CacheImplementationResponse response = null;
		try {
			log.debug("Calling cache service to set data in cache, request {}", requestString);
			String setCacheDataResponseStr = ServiceUtils.post(requestUrl, requestString);
			if (null != setCacheDataResponseStr) {
				response = GsonUtils.getObject(setCacheDataResponseStr, CacheImplementationResponse.class);
				if (StatusEnum.Failure.equals(response.getStatus())) {
					log.error("Error in cache service - Could not get aspectratio data from cache");

				}

			}

		} catch (Exception ex) {
			log.error("An error occured in AspectRatioRemoteCache.pullAspectRatioDataFromCache {}", ex);
			throw new BusinessException("ASPECT_RATIO_ERR", ex.getMessage(), ex.getCause());
		}
		return response;
	}
}
