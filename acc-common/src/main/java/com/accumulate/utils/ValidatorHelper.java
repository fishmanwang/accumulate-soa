package com.accumulate.utils;


import com.accumulate.exception.ApplicationException;
import com.accumulate.exception.CommonErrorCode;
import com.accumulate.exception.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
* 校验工具类
* Created by tjwang on 2017/1/12.
*/
public class ValidatorHelper {

	/**
	 * 构造错误键值
	 * @param key
	 * @param message
     */
	public static void error(String key, String message) {
		ApplicationException e =  ApplicationException.build(CommonErrorCode.VALIDATION_ERROR, message);
		ValidationError validationError = ValidationError.build(key, message);
		e.getValidationErrors().add(validationError);
		throw e;
	}

	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	/**
	 * 校验对象。对象中包含限制条件。
	 * TODO 针对内嵌对象查询，会有一些问题。
	 * @param obj
     */
	public static void validate(Object obj) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraints = validator.validate(obj);
		if (constraints.size() > 0) {
			ApplicationException e = ApplicationException.build(CommonErrorCode.VALIDATION_ERROR);
			for (ConstraintViolation<Object> constraint : constraints) {
				String key = constraint.getPropertyPath().toString();
				String message = constraint.getMessage();
				ValidationError validationError = ValidationError.build(key, message);
				e.getValidationErrors().add(validationError);
			}
			throw e;
		}
	}

}
