package com.mc.citylocator.util;

import static com.mc.citylocator.util.CityLocatorConstants.CITY_NAME_REGEX;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class CityLocatorUtil {
	
	public static void validateInput(String origin, String destination) throws ValidateInputException {
		if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
			throw new ValidateInputException("Origin Or Destination is null or empty string");
		}
		if(!isValid(origin, CITY_NAME_REGEX) || !isValid(destination, CITY_NAME_REGEX)) {
			throw new ValidateInputException("City Name for Origin Or Destination has none alpha charater");
		}
 
	}
	
	public static boolean isValid(String pathVarible, String regex) {
		if(pathVarible == null || regex == null) {
			return false;
		}
		if(Pattern.compile(regex).matcher(pathVarible).matches())
			return true;
		else
			return false;
	}
	
}
