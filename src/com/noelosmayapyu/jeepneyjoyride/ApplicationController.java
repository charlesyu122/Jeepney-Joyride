package com.noelosmayapyu.jeepneyjoyride;

import java.util.ArrayList;

import android.app.Application;

import com.google.android.gms.maps.model.LatLng;

public class ApplicationController extends Application{

	LatLng source, destination;
	ArrayList<Landmark> landmarks;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	// Setters Getters
	public void setSource(LatLng source){
		this.source = source;
	}
	public void setDestination(LatLng destination){
		this.destination = destination;
	}
	public void setLandmarks(ArrayList<Landmark> list){
		this.landmarks = list;
	}
	public LatLng getSource(){
		return this.source;
	}
	public LatLng getDesttion(){
		return this.destination;
	}
	public ArrayList<Landmark> getLandmarks(){
		return this.landmarks;
	}
	
	
}
