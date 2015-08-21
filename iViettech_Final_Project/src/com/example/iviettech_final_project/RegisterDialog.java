package com.example.iviettech_final_project;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.iviettech_final_project_database.DatabaseHandler;
import com.example.iviettech_final_project_database.UserFunctions;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterDialog extends Dialog {
	Context context;
	Button submitButton;
	Button cancelButton;
	EditText firstNameET, lastNameET, userNameET, passwordET,
			confirmPasswordET, emailET, cityET, genderET, ageET;
	TextView registerErrorMsg;

	private static String KEY_SUCCESS = "success";
	
	private static final String TAG_NAME = "Register_Dialog";
	
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

	public RegisterDialog(Context context) {
		super(context);
		this.context = context;
		setTitle("Guest Sign On");
		setContentView(R.layout.register_dialog);

		submitButton = (Button) findViewById(R.id.bt_register_ok);
		cancelButton = (Button) findViewById(R.id.bt_register_cancel);

		lastNameET = (EditText) findViewById(R.id.et_firstname);
		firstNameET = (EditText) findViewById(R.id.et_lastname);
		userNameET = (EditText) findViewById(R.id.et_username_register);
		passwordET = (EditText) findViewById(R.id.et_password_register);
		confirmPasswordET = (EditText) findViewById(R.id.et_confirm_password_res);
		emailET = (EditText) findViewById(R.id.et_email_address);
		genderET = (EditText) findViewById(R.id.et_gender_res);
		cityET = (EditText) findViewById(R.id.et_city_res);
		ageET = (EditText) findViewById(R.id.et_age_res);

		registerErrorMsg = (TextView) findViewById(R.id.register_error);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dismiss();
			}
		});

		submitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new RegisterTask().execute();
				

			}
		});
	}

	private class RegisterTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			register();
			return null;
		}
		
	}
	
	private void register() {

		String username = userNameET.getText().toString();
		String password = passwordET.getText().toString();
		String confirmPassword = confirmPasswordET.getText().toString();
		String email = emailET.getText().toString();
		String firstname = firstNameET.getText().toString();
		String lastname = lastNameET.getText().toString();
		String gender = genderET.getText().toString();
		String age = ageET.getText().toString();
		String city = cityET.getText().toString();

		UserFunctions userFunction = new UserFunctions();
		Log.i(TAG_NAME, "Truoc register");
		JSONObject json = userFunction.registerUser(username, password, email,
				firstname, lastname, city, Integer.parseInt(gender),
				Integer.parseInt(age), "zzz");

		
		// Kiểm tra việc phản hồi từ dữ liệu
		try {
			if (json.getString(KEY_SUCCESS) != null) {
				String res = json.getString(KEY_SUCCESS);
				Log.i(TAG_NAME, "Kiem tra phan hoi du lieu");
				// Nếu đăng ký thành công
				if (Integer.parseInt(res) == 1) {
					// Lưu trữ thông tin người dùng vào database
					DatabaseHandler db = new DatabaseHandler(context);
					JSONObject json_user = json.getJSONObject("user");
					// Xóa tất cả dữ liệu
					userFunction.logoutUser(context);
					db.addUser(json_user.getString(KEY_USERNAME),
							json_user.getString(KEY_PASSWORD),
							json_user.getString(KEY_EMAIL),
							json_user.getString(KEY_FIRSTNAME),
							json_user.getString(KEY_LASTNAME),
							json_user.getString(KEY_CITY),
							Integer.parseInt(json_user.getString(KEY_GENDER)),
							Integer.parseInt(json_user.getString(KEY_AGE)),
							json_user.getString(KEY_AVATAR));
					dismiss();		
					registerErrorMsg.setText("Đăng ký thành công tài khoản");
				} else {
					//registerErrorMsg.setText("Tài khoản đã tồn tại");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
