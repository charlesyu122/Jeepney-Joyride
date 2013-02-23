package com.noelosmayapyu.jeepneyjoyride;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DestinationActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destination);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.activity_main, menu);

//		EditText add = null;
//
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//			View v = menu.findItem(R.id.report_traffic).getActionView();
//
//			if (v != null) {
//				add = (EditText) v.findViewById(R.id.title);
//			}
//		}
//
//		if (add != null) {
//			add.setOnEditorActionListener(onSearch);
//		}

		return (super.onCreateOptionsMenu(menu));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.report_traffic:
			//do here
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}

}
