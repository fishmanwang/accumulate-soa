package com.accumulate.utils;

import org.springframework.util.Assert;

/**
 * 枚举相关的工具，用于检查枚举值是否合法等。
 * 
 * @author jintao
 * 
 */
public class EnumUtils {

	/**
	 * 校验字符串是否属于某个枚举类型
	 * @param enumClass
	 * @param enumValue
	 * @return
	 */
	public static boolean checkEnumValues(Class<?> enumClass, String enumValue) {
		Assert.hasText(enumValue);
		Assert.isTrue(enumClass.isEnum());
		Enum<?>[] enums = (Enum[]) enumClass.getEnumConstants();

		for (Enum<?> e : enums) {
			if (enumValue.equals(e.name())) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
	}
}
