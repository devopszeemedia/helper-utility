package com.zshop.helper.utils.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;

@Documented
@Min(value = 0, message = "add a min msg")
@Digits(fraction = 0, integer = 10, message = "add a digit msg")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface NumberFormatValidation {

	String message() default "invalid number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
