package com.zshop.helper.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {

	@Override
	public void copyProperty(Object dest, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if (value == null || StringUtils.isEmpty(String.valueOf(value)))
			return;
		super.copyProperty(dest, name, value);
	}

}
