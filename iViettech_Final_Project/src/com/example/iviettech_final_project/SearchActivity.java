package com.example.iviettech_final_project;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iviettech_final_project_database.DatabaseHandler;
import com.example.iviettech_final_project_database.UserFunctions;

public class SearchActivity extends Activity implements OnItemClickListener {
	private static String KEY_SUCCESS = "success";

	// Restaurant Table Columns names
	private static final String KEY_ID_RES = "id";
	private static final String KEY_RESTAURANT_NAME = "Name";
	private static final String KEY_TIME_OPEN = "OpenTime";
	private static final String KEY_TIME_CLOSE = "CloseTime";
	private static final String KEY_LAT_X = "LatX";
	private static final String KEY_LAT_Y = "LatY";
	private static final String KEY_CITY_RES = "City";
	private static final String KEY_RANK = "Rank";
	private static final String KEY_PHONE = "Phone";
	private static final String KEY_ADDRESS = "Address";
	private static final String KEY_LOGO = "Logo";

	private EditText citySearch;
	private ListView restaurantListView;
	private RestaurantArrayAdapter restaurantArrayAdapter;
	private ArrayList<Restaurant> restaurants;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		citySearch = (EditText) findViewById(R.id.et_search);
		restaurantListView = (ListView) findViewById(R.id.listview_restaurant);	
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		db.resetTableRestaurant();
		restaurants = db.getRestaurantDetails();
		restaurantArrayAdapter = new RestaurantArrayAdapter(this,
				R.layout.restaurant_listview, restaurants);
		restaurantListView.setAdapter(restaurantArrayAdapter);
		restaurantListView.setOnItemClickListener(this);
		searchButton = (Button) findViewById(R.id.bt_search);
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (citySearch.getText().toString() == "") {
					Toast.makeText(getApplicationContext(), "Hãy nhập thông tin để tìm kiếm!", Toast.LENGTH_SHORT).show();
					return;
				}
				else {
					search();
				}
				
				
			}
		});
		searchButton.setBackgroundResource(R.drawable.ic_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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

	private class SearchTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String cityStr = citySearch.getText().toString();
			UserFunctions userFunctions = new UserFunctions();
			ArrayList<JSONObject> jsonArray = userFunctions
					.searchRestaurantByCity(cityStr);
			try {

				DatabaseHandler db = new DatabaseHandler(
						getApplicationContext());

				// Xóa toàn bộ dữ liệu trong Database
				db.resetTableRestaurant();
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject json_res = jsonArray.get(i);
					db.addRestaurant(json_res.getString(KEY_RESTAURANT_NAME),
							json_res.getString(KEY_TIME_OPEN),
							json_res.getString(KEY_TIME_CLOSE),
							json_res.getString(KEY_LAT_X),
							json_res.getString(KEY_LAT_Y),
							json_res.getString(KEY_CITY_RES),
							json_res.getString(KEY_RANK),
							json_res.getString(KEY_PHONE),
							json_res.getString(KEY_ADDRESS),
							json_res.getString(KEY_LOGO));
					
					Log.i("Search", json_res.getString(KEY_RESTAURANT_NAME) +
							json_res.getString(KEY_TIME_OPEN) +
							json_res.getString(KEY_TIME_CLOSE) +
							json_res.getString(KEY_LAT_X) +
							json_res.getString(KEY_LAT_Y) +
							json_res.getString(KEY_CITY_RES) +
							json_res.getString(KEY_RANK) +
							json_res.getString(KEY_PHONE) +
							json_res.getString(KEY_ADDRESS) +
							json_res.getString(KEY_LOGO));
					Restaurant res = new Restaurant();
					res.setRestaurantName(json_res.getString(KEY_RESTAURANT_NAME));
					res.setTimeClose(json_res.getString(KEY_TIME_CLOSE));
					res.setTimeOpen(json_res.getString(KEY_TIME_OPEN));
					res.setLatX(json_res.getString(KEY_LAT_X));
					res.setLatY(json_res.getString(KEY_LAT_Y));
					res.setCity(json_res.getString(KEY_CITY_RES));
					res.setRank(json_res.getString(KEY_RANK));
					res.setPhone(json_res.getString(KEY_PHONE));
					res.setAddress(json_res.getString(KEY_ADDRESS));
					res.setLogo(json_res.getString(KEY_LOGO));
					restaurants.add(res);
				}
			
				
				for (int i = 0; i < restaurants.size(); i++) {
					String resName = restaurants.get(i).getRestaurantName();
					Log.i("Search", resName);
				}
				

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			restaurantArrayAdapter.notifyDataSetChanged();
		}

	}

	public void search() {
		restaurants.clear();
		restaurantArrayAdapter.notifyDataSetChanged();
		new SearchTask().execute();
		

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		Restaurant restaurant = restaurants.get(position);
		Intent restaurantIntent = new Intent(SearchActivity.this, RestaurantActivity.class);
		restaurantIntent.putExtra("Restaurant", restaurant);
		startActivity(restaurantIntent);
	
	}

}
