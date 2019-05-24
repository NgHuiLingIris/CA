package com.example.portal.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CompHourValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidCompHour {
	String message() default "Compensation leave hour must be a positive multiple of 0.5";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
