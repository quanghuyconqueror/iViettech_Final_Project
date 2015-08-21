package com.example.iviettech_final_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RestaurantActivity extends Activity {
	Restaurant restaurant;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		Intent calledIntent = getIntent();
		restaurant = (Restaurant) calledIntent.getSerializableExtra("Restaurant");
		Toast.makeText(this, restaurant.toString(), Toast.LENGTH_LONG).show();
		ShowPagerAdapter showPagerAdapter = new ShowPagerAdapter();
	    ViewPager showPager = (ViewPager) findViewById(R.id.show_image_res);
	    showPager.setAdapter(showPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant, menu);
		return true;
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
