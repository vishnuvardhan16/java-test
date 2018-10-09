package com.h2rd.refactoring.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
	public static boolean validate(String email) {
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
		
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

}
