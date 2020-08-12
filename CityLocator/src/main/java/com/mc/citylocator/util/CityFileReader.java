package com.mc.citylocator.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CityFileReader {
	private static CityFileReader singletonInstance = null;
	private static List<String> pairedCities = new ArrayList<String>();

	private CityFileReader() {
		init();
	}
	
	public static CityFileReader getInstance() {
		if(singletonInstance == null) {
			synchronized (CityFileReader .class) {
                if (singletonInstance == null) {
                	singletonInstance = new CityFileReader();
                }
            }
		}
		return singletonInstance;
	}
	
	private void init() {
		try {
			pairedCities = Files.lines(new File("city.txt").toPath())
					.map(s -> s.trim().toLowerCase()) 
					.filter(s -> !s.isEmpty())
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getCityList(){
		return pairedCities;
	}
	

}
