package com.example.portal.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.portal.model.Leave;

public class LeaveValidator implements ConstraintValidator<IsValidLeave, Leave> {

    public void initialize(IsValidLeave constraintAnnotation) {
    }
    
	@Override
	public boolean isValid(Leave value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value.getFromDate().toString().compareTo(value.getToDate().toString()) > 0) {
			return false;
		} else {
			return true;
		}
	}
}
