package com.example.iviettech_final_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends Activity implements OnClickListener {
	Restaurant restaurant;
	Button locationRestaurant, backButton;
	TextView tvNameRestaurant, tvTimeOpenClose, tvAddress, tvPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		Intent calledIntent = getIntent();
		restaurant = (Restaurant) calledIntent.getSerializableExtra("Restaurant");
		//Toast.makeText(this, restaurant.toString(), Toast.LENGTH_LONG).show();
		ShowPagerAdapter showPagerAdapter = new ShowPagerAdapter();
	    ViewPager showPager = (ViewPager) findViewById(R.id.show_image_res);
	    showPager.setAdapter(showPagerAdapter);
	    locationRestaurant = (Button) findViewById(R.id.bt_location_res);
	    backButton = (Button) findViewById(R.id.btnback);
	    backButton.setOnClickListener(this);
	    locationRestaurant.setOnClickListener(this);
	    
	    tvNameRestaurant = (TextView) findViewById(R.id.txtname);
	    tvTimeOpenClose = (TextView) findViewById(R.id.txt_time_open_close);
	    tvAddress = (TextView) findViewById(R.id.txt_address);
	    tvPhone = (TextView) findViewById(R.id.txt_phone);
	    
	    tvNameRestaurant.setText(restaurant.getRestaurantName());
	    tvTimeOpenClose.setText(restaurant.getTimeOpen() + " - " + restaurant.getTimeClose());
	    tvAddress.setText(restaurant.getAddress());
	    tvPhone.setText(restaurant.getPhone());
	    
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_location_res:
			goLocationRestaurant();
			break;
		case R.id.btnback:
			finish();
			break;
		default:
			break;
		}
		
	}
	
	void goLocationRestaurant() {
		Intent mapIntent = new Intent(RestaurantActivity.this, MapActivity.class);
		mapIntent.putExtra("ClassForm", RestaurantActivity.class.toString());
		mapIntent.putExtra("Restaurant", restaurant);
		startActivity(mapIntent);
	}
}
