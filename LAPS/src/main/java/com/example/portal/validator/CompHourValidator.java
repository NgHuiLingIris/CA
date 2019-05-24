package com.example.portal.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompHourValidator implements ConstraintValidator<IsValidCompHour, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value >= 0 && ((Math.ceil(value) - value==0)||(Math.ceil(value) - value==0.5)))
				{
			return true;
		} else {
			return false;
		}
	}

}
