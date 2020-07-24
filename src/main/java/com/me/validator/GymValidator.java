/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.me.validator;

/**
 *
 * @author koppu
 */

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.Gym;



public class GymValidator implements Validator{

	public boolean supports(Class aClass) {
		return aClass.equals(Gym.class);
	}

	public void validate(Object obj, Errors errors) {
		Gym comp = (Gym) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gymName", "error.invalid.comp", "Gym Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gymAddress", "error.invalid.comp", "Gym Address Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gymDescription", "error.invalid.comp", "Gym Description Required");
		
		// check if user exists
		
	}
	
}

