package com.noelosmayapyu.jeepneyjoyride;

import com.example.jeepneyjoyride.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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

	
	
	//transitions
	
		private void goToNext(View view) {
			Intent intent = new Intent(this, DetailsActivity.class);
			startActivityForResult(intent, 500);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		}
		
		private void goToPrev(View view) {
			Intent intent = new Intent(this, SourceActivity.class);
			startActivityForResult(intent, 500);
			overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
		}
		

}
