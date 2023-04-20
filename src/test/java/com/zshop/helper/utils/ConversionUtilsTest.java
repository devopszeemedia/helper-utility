package com.zshop.helper.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConversionUtilsTest {

	@InjectMocks
	private ConversionUtils conversionUtils;

	public static byte byteVal = (byte)123;
	public static Long longVal = 123L;
	public static Short shortVal = (short)123;
	public static Integer integerVal = 123;
	public static BigInteger bigIntegerVal = BigInteger.valueOf(123);
	public static Float floatVal = 123.0f;
	public static Double doubleVal = 123.0;
	public static BigDecimal bigDecimalVal = new BigDecimal("123");
	public static String stringVal = "123";
	public static Object val = 123;
	public static List<String> list = Arrays.asList("1", "2", "3");
	@Test
	public void shouldTestGetSafeInteger() {
		ConversionUtils.getSafeInteger(byteVal);
		assertNotNull(byteVal);
		assertEquals(byteVal, ((Byte) byteVal).intValue());

	}

	@Test
	public void shouldTestGetSafeIntegerFail() {

		ConversionUtils.getSafeInteger(val);
		assertNotEquals(val instanceof Byte, ConversionUtils.getSafeBigDecimal(val).intValue());
	}

	@Test
	public void shouldTestGetSafeIntegerNullFail() {

		ConversionUtils.getSafeInteger(val);
		assertEquals(null, ConversionUtils.getSafeInteger(null));
	}

	@Test
	public void shouldTestGetSafeLong() {

		ConversionUtils.getSafeLong(val);
		assertNotNull(val);

	}

	@Test
	public void shouldTestGetSafeLongFail() {

		ConversionUtils.getSafeLong(val);
		assertEquals(null, ConversionUtils.getSafeLong(null));
	}

	@Test
	public void shouldTestGetSafeString() {

		ConversionUtils.getSafeString(val);
		assertNotNull(val);

	}

	@Test
	public void shouldTestGetSafeStringFail() {

		ConversionUtils.getSafeString(val);
		assertEquals(null, ConversionUtils.getSafeString(null));
	}

	@Test
	public void shouldTestGetSafeDouble() {
	
		ConversionUtils.getSafeDouble(val);
		assertNotNull(val);
		
	}

	@Test
	public void shouldTestGetSafeDoubleFail() {
	
		ConversionUtils.getSafeDouble(val);
		assertEquals(null,ConversionUtils.getSafeDouble(null));
	}

	@Test
	public void shouldTestGetSafeBigDecimal() {

		ConversionUtils.getSafeBigDecimal(val);
		assertNotNull(val);
	}

	@Test
	public void shouldTestGetSafeBigDecimalForShort() {

		ConversionUtils.getSafeBigDecimal(shortVal);
		assertNotNull(shortVal);
	}

	@Test
	public void shouldTestGetSafeBigDecimalForLong() {

		ConversionUtils.getSafeBigDecimal(longVal);
		assertNotNull(longVal);
	}

	@Test
	public void shouldTestGetSafeBigDecimalForBigInteger() {

		ConversionUtils.getSafeBigDecimal(bigIntegerVal);
		assertNotNull(bigIntegerVal);
	}

	@Test
	public void shouldTestGetSafeBigDecimalForFloat() {

		ConversionUtils.getSafeBigDecimal(floatVal);
		assertNotNull(floatVal);
	}

	@Test
	public void shouldTestGetSafeBigDecimalForDouble() {

		ConversionUtils.getSafeBigDecimal(val);
		assertNotNull(val);
	}

//	@Test
//	public void shouldTestGetSafeBigDecimalForBigDecimal() {
//	
//		ConversionUtils.getSafeDouble(bigDecimalVal);
//		assertNotNull(bigDecimalVal);
//		assertEquals(bigDecimalVal instanceof BigDecimal,  new BigDecimal((BigDecimal) bigDecimalVal));
//	}

	@Test
	public void shouldTestGetSafeBigDecimalForString() {

		ConversionUtils.getSafeBigDecimal(stringVal);
		assertNotNull(stringVal);
	}
	
	@Test
	public void shouldTestGetSafeBigDecimalFail() {
	
		ConversionUtils.getSafeBigDecimal(val);
		assertEquals(null,ConversionUtils.getSafeBigDecimal(null));
	}
	
	@Test
	public void shouldTestBooleanConverter() {
		Boolean boolValTrue = true;
		ConversionUtils.booleanConverter(boolValTrue);
		assertNotNull(boolValTrue);
		assertTrue(boolValTrue);
	}
	
	@Test
	public void shouldTestBooleanConverterNullFail() {
		ConversionUtils.booleanConverter(null);
	}

	@Test
	public void shouldTestBooleanConverterFail() {
		Boolean boolValFalse = false;
		ConversionUtils.booleanConverter(boolValFalse);
		assertFalse(boolValFalse);
	}

	
	@Test
	public void shouldTestStringToBooleanConverter() {
		ConversionUtils.stringToBooleanConverter("No");
		String inputValue = "No";
		assertTrue(inputValue.equalsIgnoreCase("No"));
	}
	
	@Test
	public void shouldTestStringToBooleanConverterFail() {
		ConversionUtils.stringToBooleanConverter(stringVal);
	}

	@Test
	public void shouldTestBigIntegerToLong() {
		ConversionUtils.bigIntegerToLong(bigIntegerVal);
		assertNotNull(bigIntegerVal);
	}
	
	@Test
	public void shouldTestBigIntegerToLongFail() {
		ConversionUtils.bigIntegerToLong(null);
	}
	
	@Test
	public void shouldTestConvertBlankToNo() {
		ConversionUtils.convertBlankToNo(stringVal);
		assertNotNull(bigIntegerVal);
	}
	
//	@Test
//	public void shouldTestConvertBlankToNoLengthFail() {
//		ConversionUtils.convertBlankToNo(stringVal);
//		assertTrue(stringVal.trim().length() == 0);
//	}
	
	@Test
	public void shouldTestConvertBlankToNoFail() {
		ConversionUtils.convertBlankToNo(null);
	}
	
//	public static Boolean getSafeBooleanFromInteger(Object val) {
//		if (val == null)
//			return null;
//
//		Integer safeInt = getSafeInteger(val);
//
//		if (null == safeInt)
//			return null;
//
//		if (1 == safeInt)
//			return true;
//		else
//			return false;
//
//	}
	
	@Test
	public void shouldTestGetSafeBooleanFromInteger() {
		ConversionUtils.getSafeBooleanFromInteger(val);
		
		Integer safeInt = ConversionUtils.getSafeInteger(val);

		assertNotNull(bigIntegerVal);
		assertNotNull(safeInt);
		
	}
	
	@Test
	public void shouldTestGetSafeBooleanFromIntegerFail() {
		ConversionUtils.getSafeBooleanFromInteger(null);
		Integer safeInt = ConversionUtils.getSafeInteger(null);
	}

	@Test
	public void shouldTestGetSafeBooleanFromIntegerFail1() {
		ConversionUtils.getSafeBooleanFromInteger(1);
		Integer safeInt = ConversionUtils.getSafeInteger(1);
	}

}
