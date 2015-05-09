package com.hackathon.HAC4819;

import java.util.ArrayList;

import com.hackathon.HAC4819.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	private DrawerLayout mDrawerLayout;
	SharedPreferences myprefs;
	SharedPreferences.Editor editor;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
		private CharSequence mDrawerTitle;

		// used to store app title
		private CharSequence mTitle;

		// slide menu items
		private String[] navMenuTitles;
		private TypedArray navMenuIcons;

		private ArrayList<NavDrawerItem> navDrawerItems;
		private NavDrawerListAdapter adapter;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		myprefs=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();
		// adding nav drawer items to array
				// Home
		       navDrawerItems.add(new NavDrawerItem(myprefs.getString("name", "User"),navMenuIcons.getResourceId(0, -1)));
		
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(1, -1)));
				// Find People
				
				//navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
				// Photos
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(2, -1)));
				
				navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(3, -1)));
				// Communities, Will add a counter here
				
				// Recycle the typed array
				navMenuIcons.recycle();

				mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

				// setting the nav drawer list adapter
				adapter = new NavDrawerListAdapter(getApplicationContext(),
						navDrawerItems);
				mDrawerList.setAdapter(adapter);

				// enabling action bar app icon and behaving it as toggle button
				getActionBar().setDisplayHomeAsUpEnabled(true);
				getActionBar().setHomeButtonEnabled(true);
				
				mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
						R.drawable.ic_drawer, //nav menu toggle icon
						R.string.app_name, // nav drawer open - description for accessibility
						R.string.app_name // nav drawer close - description for accessibility
				) {
					public void onDrawerClosed(View view) {
						getActionBar().setTitle(mTitle);
						// calling onPrepareOptionsMenu() to show action bar icons
						invalidateOptionsMenu();
					}

					public void onDrawerOpened(View drawerView) {
						getActionBar().setTitle(mDrawerTitle);
						// calling onPrepareOptionsMenu() to hide action bar icons
						invalidateOptionsMenu();
					}
				};
				mDrawerLayout.setDrawerListener(mDrawerToggle);
				if (savedInstanceState == null) {
					// on first time display view for first nav item
					displayView(0);
				}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	
		return super.onPrepareOptionsMenu(menu);
	}
	
	//for navigation drawer
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}

		
	}
	
	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			
		case 1:
			//Intent normalIntent = new Intent(Intent.ACTION_SEND);
			//normalIntent.setType("text/plain");
			//normalIntent.setPackage("com.katana.facebook"); // I just know the package of Facebook, the rest you will have to search for or use my method.
			//normalIntent.putExtra(Intent.EXTRA_TEXT, "Store search buddy , a nice app by Shriyansh Gautam and Yash Suresh Chandra from IIT BHU Varanasi. Download it from Google Play Store ");
			//startActivityForResult(normalIntent,5);
			fragment = new HomeFragment();
			
			
			break;
		case 2:
			fragment=new ResultFragment();
			Bundle args=new Bundle();
			args.putInt("score", -1);
			fragment.setArguments(args);
			//Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		    //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
		    //        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		    //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
		    //startActivityForResult(intent, 3);
			break;
		case 3:
			editor=myprefs.edit();
			editor.putInt("loggedin", 0);
			editor.putString("username", "");
			editor.putString("name", "");
			editor.commit();
			//Toast.makeText(getApplicationContext(), "login called", Toast.LENGTH_SHORT).show();
			Intent calllogin=new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(calllogin);
			finish();
			//Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
			//sendIntent.setData(Uri.parse("sms:"));
			//sendIntent.putExtra("sms_body", "Your fiend Shared an app with you. You can Download this app from Google play store soon..");
			//startActivity(sendIntent);
			
			break;
		case 4://Intent callList=new Intent(MainActivity.this,List.class);
				//callList.putExtra("query", "global");
				//startActivityForResult(callList,1);
			break;
		case 5:
			
			//Intent add=new Intent(MainActivity.this,Add.class);
			//add.putExtra("task","add");
			//startActivityForResult(add,1);
			break;
		case 6:
			//fragment = new AboutUs();
			break;

		default:
			break;
		}

		if (fragment != null) {
			
			
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			if(position==0){
				setTitle(navMenuTitles[position]);
			}else{
				setTitle(navMenuTitles[position-1]);
			}
			
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
