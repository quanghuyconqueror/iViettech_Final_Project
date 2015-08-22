package com.example.iviettech_final_project;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends Activity implements OnLongClickListener {
	
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_FIRSTNAME = "firstname";
	private static final String KEY_LASTNAME = "lastname";
	private static final String KEY_CITY = "city";
	private static final String KEY_GENDER = "gender";
	private static final String KEY_AGE = "age";
	private static final String KEY_AVATAR = "avatar";
	
	ArrayList<Integer> editTextID;
	
	Button submitButton;
	EditText firstNameET, lastNameET, passwordET,
			 cityET, genderET, ageET;
	TextView registerErrorMsg, userNameTV, emailTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		
		editTextID = new ArrayList<Integer>();
		
		submitButton = (Button) findViewById(R.id.bt_submit_user);
		submitButton.setVisibility(View.INVISIBLE);
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				submitUser();
				for (int i = 0; i < editTextID.size(); i++) {
					EditText et = (EditText) findViewById(editTextID.get(i));
					et.setCursorVisible(false);
					et.setFocusable(false);
					et.setFocusableInTouchMode(false);
					
				}
				
			}
		});
		

		lastNameET = (EditText) findViewById(R.id.et_firstname_user);
		firstNameET = (EditText) findViewById(R.id.et_lastname_user);
		userNameTV = (TextView) findViewById(R.id.tv_username_user);
		passwordET = (EditText) findViewById(R.id.et_password_user);
		emailTV = (TextView) findViewById(R.id.tv_email_address_user);
		genderET = (EditText) findViewById(R.id.et_gender_user);
		cityET = (EditText) findViewById(R.id.et_city_user);
		ageET = (EditText) findViewById(R.id.et_age_user);
		
		SharedPreferences settings = getApplicationContext().getSharedPreferences("UserPreferences", 0);
		
		lastNameET.setText(settings.getString(KEY_LASTNAME, ""));
		firstNameET.setText(settings.getString(KEY_FIRSTNAME, ""));
		userNameTV.setText(settings.getString(KEY_USERNAME, ""));
		passwordET.setText(settings.getString(KEY_PASSWORD, ""));
		emailTV.setText(settings.getString(KEY_EMAIL, ""));
		genderET.setText(settings.getString(KEY_GENDER, ""));
		cityET.setText(settings.getString(KEY_CITY, ""));
		ageET.setText(settings.getString(KEY_AGE, ""));

		lastNameET.setFocusableInTouchMode(false);
		firstNameET.setFocusableInTouchMode(false);
		passwordET.setFocusableInTouchMode(false);
		genderET.setFocusableInTouchMode(false);
		cityET.setFocusableInTouchMode(false);
		ageET.setFocusableInTouchMode(false);
		
		lastNameET.setOnLongClickListener(this);
		firstNameET.setOnLongClickListener(this);
		passwordET.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				ChangePasswordDialog changeDialog = new ChangePasswordDialog(UserActivity.this);
				changeDialog.show();
				return false;
			}
		});
		genderET.setOnLongClickListener(this);
		cityET.setOnLongClickListener(this);
		ageET.setOnLongClickListener(this);
		
		registerErrorMsg = (TextView) findViewById(R.id.user_error);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
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
	public boolean onLongClick(View v) {
		EditText editTextSelected = (EditText) v;
		editTextSelected.setFocusableInTouchMode(true);
		editTextSelected.setFocusable(true);
		editTextID.add(editTextSelected.getId());
		submitButton.setVisibility(View.VISIBLE);
		editTextSelected.requestFocus();
		return false;
	}
	
	private void submitUser() {
		
	}
}
