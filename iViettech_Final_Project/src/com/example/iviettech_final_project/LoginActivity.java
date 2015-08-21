package com.example.iviettech_final_project;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.iviettech_final_project_database.DatabaseHandler;
import com.example.iviettech_final_project_database.UserFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	Button createAccountButton;
	Button loginButton;
	Button forgetPasswordButton;

	EditText username, password;

	private static String KEY_SUCCESS = "success";
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_FIRSTNAME = "firstname";
	private static final String KEY_LASTNAME = "lastname";
	private static final String KEY_CITY = "city";
	private static final String KEY_GENDER = "gender";
	private static final String KEY_AGE = "age";
	private static final String KEY_AVATAR = "avatar";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		username = (EditText) findViewById(R.id.et_username);
		password = (EditText) findViewById(R.id.et_password);

		createAccountButton = (Button) findViewById(R.id.bt_create_account);
		loginButton = (Button) findViewById(R.id.bt_login);
		forgetPasswordButton = (Button) findViewById(R.id.bt_forgot_password);

		createAccountButton.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		forgetPasswordButton.setOnClickListener(this);

	}

	private class LoginTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			login();
			return null;
		}
		
	}
	
	public void login() {
		String usernameString = username.getText().toString();
		String passwordString = password.getText().toString();
		UserFunctions userFunctions = new UserFunctions();
		JSONObject json = userFunctions.loginUser(usernameString, passwordString);
		try {
			if (json.getString(KEY_SUCCESS) != null) {
			 	String res = json.getString(KEY_SUCCESS);
			 	if (Integer.parseInt(res) == 1) {
			        	// user successfully logged in
			        	//Lưu trữ thông tin chi iết người dùng trong database
			        	DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			        	JSONObject json_user = json.getJSONObject("user");
			        	
			        	//Xóa toàn bộ dữ liệu trong Database
			        	userFunctions.logoutUser(getApplicationContext());
			        	db.addUser(json_user.getString(KEY_USERNAME),
								json_user.getString(KEY_PASSWORD),
								json_user.getString(KEY_EMAIL),
								json_user.getString(KEY_FIRSTNAME),
								json_user.getString(KEY_LASTNAME),
								json_user.getString(KEY_CITY),
								Integer.parseInt(json_user.getString(KEY_GENDER)),
								Integer.parseInt(json_user.getString(KEY_AGE)),
								json_user.getString(KEY_AVATAR));   
			        	
			        	final String userLogin = json_user.getString(KEY_USERNAME);
			        	runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(LoginActivity.this, userLogin + ", bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();				
							}
						});
			        	
			        	Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
			        	mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        	startActivity(mainActivity);	
			        	
			 	}
			 	else {
			 		runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
							username.setText("");
					 		password.setText("");
					 		username.requestFocus();
						}
					});
			 		
			 		
			 	}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bt_create_account:
			RegisterDialog registerDialog = new RegisterDialog(this);
			registerDialog.show();
			break;
		case R.id.bt_login:
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					login();
					
				}
			}).start();
			
		default:
			break;
		}

	}

}
