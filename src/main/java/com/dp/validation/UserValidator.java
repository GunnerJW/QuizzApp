package com.dp.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dp.model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "login", "login.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
		ValidationUtils.rejectIfEmpty(errors, "role", "role.empty");
		User u=(User)target;
		if(!u.getPassword().equals(u.getConfirmPassword()))
			errors.rejectValue("confirmPassword", "nomatch");
	}

}
