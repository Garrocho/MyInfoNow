package com.mycurrentip;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyCurrentIP extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_current_ip);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_current_i, menu);
		return true;
	}

}
