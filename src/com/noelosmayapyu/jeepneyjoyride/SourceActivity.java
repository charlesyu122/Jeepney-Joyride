package com.noelosmayapyu.jeepneyjoyride;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SourceActivity extends Activity implements LocationListener{
	
	// Attributes
	private GoogleMap map;
	private ApplicationController AC;
	private LocationManager locationManager;
	private String provider;
	
	// View Attributes
	Button btnFindMe, btnPrev, btnNext;
	Spinner spinLandmarks;
	
	// Attributes for DB
	JSONParser jParser = new JSONParser();
	private ProgressDialog pDialog;	
	private static String url_get_landmarks = "http://jeepneyjoyride.site40.net/api/get_landmarks";
	JSONArray landmark = null;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_LANDMARK = "landmark";
	ArrayList<Landmark> landmarkList = new ArrayList<Landmark>();
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.activity_main, menu);
		return (super.onCreateOptionsMenu(menu));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_source);
		setup();
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.report_traffic:
			 final Dialog dialog = new Dialog(this);
			   dialog.setContentView(R.layout.mydialog);
			   dialog.setTitle("Title...");
			   
			   TextView report = (TextView) dialog.findViewById(R.id.report);
			   TextView postMessage = (TextView) dialog.findViewById(R.id.postMessage);
			   
			   Button b=(Button)dialog.findViewById(R.id.reportButton);
			   b.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					//add here sumting
				}});
			  
			   dialog.show();
						
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}

	private void setup(){
		btnFindMe = (Button)findViewById(R.id.btnFindMe);
		btnNext = (Button)findViewById(R.id.btnNext);
		btnPrev = (Button)findViewById(R.id.btnPrev);
		btnPrev.setVisibility(View.INVISIBLE);
		
		// Setup maps and location
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		LatLng centerOfCebu = new LatLng(10.314582, 123.885098);
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(centerOfCebu, 11));
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    provider = locationManager.getBestProvider(new Criteria(), true);
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goToNext();
			}
		});
		
		btnFindMe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Get the location manager
			    locationManager.requestLocationUpdates(provider, 10000, 10, SourceActivity.this);
			}
		});
		
		// Retrieve landmarks
		//new RetrieveLandmarks().execute();
		//AC = (ApplicationController)getApplicationContext();
		//AC.setLandmarks(landmarkList);
		
	}
	
	private void goToNext(){
		Intent intent = new Intent(this, DestinationActivity.class);
		startActivityForResult(intent, 500);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
	
	class RetrieveLandmarks extends AsyncTask<String, String, String>{
		
		String id, name, latitude, longitude;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(SourceActivity.this);
			pDialog.setMessage("Retrieving landmarks...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
			//Building parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			//Getting JSONObject from url
			JSONObject json = jParser.makeHttpRequest(url_get_landmarks, params);
			
			//Check log cat for JSON response
			Log.d("Landmarks received:", json.toString());
			
			try{
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);
				if(success == 1){
					// User is found
					// Get user
					landmark = json.getJSONArray(TAG_LANDMARK);
					
					if(landmark.length() == 1){
						JSONObject temp = landmark.getJSONObject(0);
						// Store each json item in variable
						id = temp.getString("l_id");
						name = temp.getString("name");
						latitude = temp.getString("latitude");
						longitude = temp.getString("longitude");
						
						landmarkList.add(new Landmark(id, name, new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))));
					}
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		// Add a marker
		Log.d("HERE", "location!");
		double curLat = location.getLatitude();
		double curLong = location.getLongitude();
		LatLng curLoc = new LatLng(curLat, curLong);
		map.addMarker(new MarkerOptions().position(curLoc));
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(curLoc, 17));
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	private Landmark computeNearest(LatLng curLoc){
		ArrayList<Integer> differences = new ArrayList<Integer>();
		for(int i = 0; i<landmarkList.size(); i++){
			differences.add(computeDifference(curLoc, landmarkList.get(i).location));
		}	
		// Find smallest
		int smallNdx = 0, temp;
		for(int i=1 ; i<differences.size(); i++){
			if(differences.get(i) < differences.get(smallNdx)){
				smallNdx = i;
			}
		}
		return landmarkList.get(smallNdx);
	}
	
	private int computeDifference(LatLng loc, LatLng landLoc){
		return (int)Math.sqrt(Math.pow(loc.latitude - landLoc.latitude, 2) + Math.pow(loc.longitude - landLoc.longitude, 2));
	}
}
