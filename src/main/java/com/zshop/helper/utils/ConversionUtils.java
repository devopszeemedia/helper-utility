package com.zshop.helper.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ConversionUtils {

	public static Integer getSafeInteger(Object val) {
		if (val == null)
			return null;

		return val instanceof Byte ? ((Byte) val).intValue() : getSafeBigDecimal(val).intValue();

	}

	public static Long getSafeLong(Object val) {
		return val != null ? getSafeBigDecimal(val).longValue() : null;
	}

	public static String getSafeString(Object val) {
		return val != null ? String.valueOf(val) : null;
	}

	public static Double getSafeDouble(Object val) {
		return val != null ? getSafeBigDecimal(val).doubleValue() : null;
	}

	public static <E> boolean isEmpty(Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}

	public static BigDecimal getSafeBigDecimal(Object val) {
		if (val == null) {
			return null;
		}

		if (val instanceof Short) {
			return new BigDecimal((Short) val);
		} else if (val instanceof Integer) {
			return new BigDecimal((Integer) val);
		} else if (val instanceof Long) {
			return new BigDecimal((Long) val);
		} else if (val instanceof BigInteger) {
			return new BigDecimal((BigInteger) val);
		} else if (val instanceof Float) {
			return new BigDecimal((Float) val);
		} else if (val instanceof Double) {
			return new BigDecimal((Double) val);
		} else if (val instanceof BigDecimal) {
			return (BigDecimal) val;
		} else if (val instanceof String) {
			return new BigDecimal((String) val);
		} else {
			return null;
		}
	}

	public static Integer booleanConverter(Boolean req) {
		return req != null && req ? 1 : 0;

	}
	
	public static boolean stringToBooleanConverter(String inputValue){
		if(inputValue.equalsIgnoreCase("No")){
			return false;
		}
		return true;
	}
	
	public static long bigIntegerToLong(final Object obj) {
		if (obj == null) {
			return 0;
		} else {
			return ((BigInteger) obj).longValue();
		}
	}
	
	public static String convertBlankToNo(String value) {
		// null value comes from excel if column is blank.
		if (value==null || value.trim().length()==0) {
			return "NO";
		}
		return value;
	}
	
	public static String convertListToString(List<String> source, String delimiter){
		String s = source.stream().map(Object::toString).collect(Collectors.joining(delimiter));
		
		return s;
	}
	
	public static Boolean getSafeBooleanFromInteger(Object val) {
		if (val == null)
			return null;

		Integer safeInt = getSafeInteger(val);
		
		if(null == safeInt)
			return null;
		
		if(1 == safeInt)
			return true;
		else
			return false;

	}
	
	public static  Set<Long> getSetFromLong(Long longVal) {
		return new HashSet<>(Arrays.asList(longVal));
	}

	public static String getSafeStringWithouDecimal(Object val) {
		if (val == null)
			return null;
		return getSafeString(getSafeInteger(val));
	}

}
