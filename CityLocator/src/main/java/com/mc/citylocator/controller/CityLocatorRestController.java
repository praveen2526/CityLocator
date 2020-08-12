package com.mc.citylocator.controller;

import static com.mc.citylocator.util.CityLocatorConstants.NO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.citylocator.controller.beans.ServiceResponse;
import com.mc.citylocator.util.ValidateInputException;

@RestController
public class CityLocatorRestController {
	
	@GetMapping("/connected")
	public ServiceResponse checkCityConnectivity(@RequestParam(required= true, value = "origin") String origin,
			@RequestParam(required= true, value = "destination") String destination) {
		ServiceResponse serviceResponse = new ServiceResponse();
		System.out.println("Origin:-" + origin);
		System.out.println("Destination:-" + destination);
		try {
			serviceResponse = CityLocatorHandler.checkCityConnectivity(origin, destination);
		} catch (ValidateInputException vie) {
			serviceResponse.setConnected(NO);
			System.out.println(vie.getMessage());
		} catch (Exception e) {
			serviceResponse.setConnected(NO);
			System.out.println(e.getMessage());
		}
		
		return serviceResponse;
	}

}
