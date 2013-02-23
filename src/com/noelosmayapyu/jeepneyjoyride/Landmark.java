package com.noelosmayapyu.jeepneyjoyride;

public class Landmark {
	
	String id;
	String landmarlName;
	double latitude;
	double longitude;
	
	public Landmark(String id, String name, double latitude, double longitude){
		this.id = id;
		this.landmarlName = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
