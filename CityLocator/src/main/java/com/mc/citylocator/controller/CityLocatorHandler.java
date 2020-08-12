package com.mc.citylocator.controller;

import static com.mc.citylocator.util.CityLocatorConstants.YES;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static com.mc.citylocator.util.CityLocatorConstants.NO;
import static com.mc.citylocator.util.CityLocatorConstants.COMMA_SPACE_SEPERATOR;

import com.mc.citylocator.controller.beans.ServiceResponse;
import com.mc.citylocator.util.CityFileReader;
import com.mc.citylocator.util.CityLocatorUtil;

public class CityLocatorHandler {
	
	public static ServiceResponse checkCityConnectivity(String origin, String destination) throws Exception{
		CityLocatorUtil.validateInput(origin, destination);	
		origin = origin.toLowerCase();
		destination = destination.toLowerCase();
		ServiceResponse serviceResponse = new ServiceResponse();
		if(checkForConnectivity(origin, destination)) {
			serviceResponse.setConnected(YES);
		}else
			serviceResponse.setConnected(NO);
		
		return serviceResponse;		
	}
	
	private static boolean checkForConnectivity(String origin, String destination) {
		System.out.println("Origin:-" + origin + " Destination:-" + destination);
		if(checkForDirectConnectivity(origin, destination)) {
			return true;
		} else {
			if(checkForIndirectConnectivityFromFirstCity(origin, destination)) {
				return true;
			}else {
				if(checkForIndirectConnectivityFromSecondCity(origin, destination)) {
					return true;
				}else 
					return false;
			}
		}
	}
	
	private static boolean checkForDirectConnectivity(String origin, String destination) {
		System.out.println("Origin:-" + origin + " Destination:-" + destination);
		List<String> cityPairs = CityFileReader.getInstance().getCityList();
		String origDest = origin + COMMA_SPACE_SEPERATOR + destination;
		String destOrig = destination + COMMA_SPACE_SEPERATOR + origin;
		if(cityPairs.contains(origDest) || cityPairs.contains(destOrig)) {
			return true;
		}
		return false;
	}
	
	private static boolean checkForIndirectConnectivityFromFirstCity(String origin, String destination) {
		System.out.println("Origin:-" + origin + " Destination:-" + destination);
		List<String> cityPairs = CityFileReader.getInstance().getCityList();
		boolean connected = false;
		if(checkForDirectConnectivity(origin, destination)) {
			connected = true;
			return connected;
		}
		for(String cityPair : cityPairs) {
			if(cityPair.contains(origin) || cityPair.contains(destination)) {
				List<String> cities = Collections.list(new StringTokenizer(cityPair, ",")).stream()
						.map(token -> (String) token.toString().trim())
						.collect(Collectors.toList());
				if(cities.get(0).equals(origin)) {
					origin = cities.get(1);
					connected = checkForIndirectConnectivityFromFirstCity(origin, destination);
					return connected;
				}
			}
		}
		return connected;
	}
	
	private static boolean checkForIndirectConnectivityFromSecondCity(String origin, String destination) {
		System.out.println("Origin:-" + origin + " Destination:-" + destination);
		List<String> cityPairs = CityFileReader.getInstance().getCityList();
		boolean connected = false;
		if(checkForDirectConnectivity(origin, destination)) {
			connected = true;
			return connected;
		}
		for(String cityPair : cityPairs) {
			if(cityPair.contains(origin) || cityPair.contains(destination)) {
				List<String> cities = Collections.list(new StringTokenizer(cityPair, ",")).stream()
						.map(token -> (String) token.toString().trim())
						.collect(Collectors.toList());
				if(cities.get(1).equals(origin)) {
					origin = cities.get(0);
					connected = checkForIndirectConnectivityFromSecondCity(origin, destination);
					return connected;
				}
			}
		}
		return connected;
	}

}
