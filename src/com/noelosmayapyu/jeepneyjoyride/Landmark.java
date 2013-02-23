package com.noelosmayapyu.jeepneyjoyride;

import com.google.android.gms.maps.model.LatLng;

public class Landmark {
	
	String id;
	String landmarlName;
	LatLng location;
	
	public Landmark(String id, String name, LatLng loc){
		this.id = id;
		this.landmarlName = name;
		this.location = loc;
	}

}
