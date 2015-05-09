package com.hackathon.HAC4819;

import com.hackathon.HAC4819.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent callmain=new Intent(SplashActivity.this,LoginActivity.class);
				// TODO Auto-generated method stub
				startActivity(callmain);
				finish();
			}
		}, 1500);
	}

	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
