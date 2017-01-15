package com.accumulate.utils;

import java.math.BigDecimal;

/**
 * 基础工具类
 */
public class CommonUtils {

	public static int convertIfNull(Integer val) {
		if (val == null) {
			return 0;
		}
		return val;
	}
	
	public static long convertIfNull(Long val) {
		if (val == null) {
			return 0l;
		}
		return val;
	}
	
	public static double convertIfNull(Double val) {
		if (val == null) {
			return 0d;
		}
		return val;
	}
	
	public static BigDecimal convertIfNull(BigDecimal val) {
		if (val == null) {
			return new BigDecimal(0);
		}
		return val;
	}
	
	public static String convertIfNull(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return str;
	}
	
	public static Object convertIfNull(Object obj) {
		if (obj instanceof Integer) {
			return convertIfNull((Integer)obj);
		} else if (obj instanceof Long) {
			return convertIfNull((Long)obj);
		} else if (obj instanceof Double) {
			return convertIfNull((Double)obj);
		}
		throw new RuntimeException(obj.getClass() + "不能转换的类型");
	}
	
	public static Double toFixed(Double val, int num) {
		if (val == null) {
			val = 0d;
		}
		BigDecimal b = new BigDecimal(val).setScale(num, BigDecimal.ROUND_HALF_DOWN);
		return b.doubleValue();
	}
	
	public static String toFixedString(Double val, int num) {
		if (val == null) {
			val = 0d;
		}
		BigDecimal b = new BigDecimal(val).setScale(num, BigDecimal.ROUND_HALF_DOWN);
		return b.toString();
	}
	
}
