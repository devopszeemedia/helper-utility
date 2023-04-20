package com.zshop.helper.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GsonInclusionExclusionUtils {

	public MappingJacksonValue includeExcludeFromResponse(BaseRequest req) {

		SimpleBeanPropertyFilter simpleBeanPropertyFilter = null;
		FilterProvider filterProvider = null;

		if (!CollectionUtils.isEmpty(req.getInclusionList())) {
			simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(req.getInclusionList());
			filterProvider = new SimpleFilterProvider().addFilter(req.getFilterName(), simpleBeanPropertyFilter);
		}
		if (!CollectionUtils.isEmpty(req.getExclusionList())) {
			simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(req.getExclusionList());
			filterProvider = new SimpleFilterProvider().addFilter(req.getFilterName(), simpleBeanPropertyFilter);
		}

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(req.getT());
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;

	}
}
