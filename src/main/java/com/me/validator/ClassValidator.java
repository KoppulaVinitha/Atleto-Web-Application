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

import com.me.pojo.ClasList;

public class ClassValidator implements Validator{

	public boolean supports(Class aClass) {
		return aClass.equals(ClasList.class);
	}

	public void validate(Object obj, Errors errors) {
		ClasList clas = (ClasList) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clasName", "error.invalid.clas", "Clas Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clasDesc", "error.invalid.clas", "Clas Description Required");
	
		
		// check if user exists
		
	}
	
}
