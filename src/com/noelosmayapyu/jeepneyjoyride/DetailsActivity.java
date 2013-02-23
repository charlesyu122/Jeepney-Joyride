package com.noelosmayapyu.jeepneyjoyride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DetailsActivity extends Activity{

    String[] jeepString = new String[] {"13C","13B", "13A", "13Z", "13E", "13S" }; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		ListView lv = (ListView) findViewById(R.id.jeepList);
	     
		ArrayAdapter<String> jeepAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, jeepString);
		lv.setAdapter(jeepAdapter);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.activity_main, menu);

		
			return (super.onCreateOptionsMenu(menu));
	}
	
	public void onGoBack(View view) {
		Intent intent = new Intent(this, DestinationActivity.class);
		startActivityForResult(intent, 500);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

}