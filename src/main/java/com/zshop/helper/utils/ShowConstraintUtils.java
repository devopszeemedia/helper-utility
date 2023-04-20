package com.zshop.helper.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zshop.helper.exception.business.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ShowConstraintUtils {

	@Autowired
	private RedisService redisCache;

	public String getShowConstraintFromCahce() throws BusinessException {

		String showConstraintCacheResp = redisCache
				.getDataFromCache(HelperBusinessConstantsEnumUtils.ShowConstraint.SHOW_CONFIG_CONSTRAINT.getSource());
		log.info("Show Constraint from cache {}", showConstraintCacheResp);
		return showConstraintCacheResp;
	}

}
