package com.zshop.helper.utils;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void test() {
		System.out.println(DateUtils.getDateFromString("2020-06-19 09:00:00", DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
	}

}
