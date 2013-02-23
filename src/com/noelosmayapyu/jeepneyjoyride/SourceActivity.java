package com.noelosmayapyu.jeepneyjoyride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class SourceActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_source);
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
	
	public void onClickNext(View view) {
		Intent intent = new Intent(this, DestinationActivity.class);
		startActivityForResult(intent, 500);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

}
