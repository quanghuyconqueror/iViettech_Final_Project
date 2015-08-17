package com.example.iviettech_final_project;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.iviettech_final_project_database.DatabaseHandler;
import com.example.iviettech_final_project_database.UserFunctions;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterDialog extends Dialog {
	Context context;
	Button submitButton;
	Button cancelButton;
	EditText firstNameET, lastNameET, userNameET, passwordET, confirmPasswordET, emailET, cityET, genderET, ageET;
	TextView registerErrorMsg;

	
	private static String KEY_SUCCESS = "success";
	private static final String KEY_ID = "ID";
   	private static final String KEY_USERNAME = "Username";
   	private static final String KEY_PASSWORD = "Password";
   	private static final String KEY_EMAIL = "Email";
   	private static final String KEY_FIRSTNAME = "Firstname";
   	private static final String KEY_LASTNAME = "Lastname";
   	private static final String KEY_CITY = "City";
   	private static final String KEY_GENDER = "Gender";
   	private static final String KEY_AGE = "Age";
   	private static final String KEY_AVATAR = "Avatar";

	
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
		
		registerErrorMsg = (TextView) findViewById(R.id.tv_register_error);
		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();		
			}
		});
		
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();
				
			}
		});
	}
	
	private void register() {
		
		String username = userNameET.getText().toString();
		String password = passwordET.getText().toString();
        String email = emailET.getText().toString();
        String firstname = firstNameET.getText().toString();
        String lastname = lastNameET.getText().toString();
        String gender = genderET.getText().toString();
        String age = ageET.getText().toString();
        String city = cityET.getText().toString();
        
        UserFunctions userFunction = new UserFunctions();
        JSONObject json = userFunction.registerUser(username, password, email, firstname, lastname, city, Integer.parseInt(gender), Integer.parseInt(age), "" );
        
        //Kiểm tra  việc phản hồi từ dữ liệu
        try {
               if (json.getString(KEY_SUCCESS) != null) {
                      registerErrorMsg.setText("");
                      String res = json.getString(KEY_SUCCESS);
                      //Nếu đăng ký thành công
                      if(Integer.parseInt(res) == 1){                                             
                             //Lưu trữ thông tin người dùng vào database
                             DatabaseHandler db = new DatabaseHandler(context);
                             JSONObject json_user = json.getJSONObject("user");
                             //Xóa tất cả dữ liệu
                             userFunction.logoutUser(context);
                             db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));                                    
                             //Chuyển qua Activity main
                             Intent mainActivity = new Intent(context, MainActivity.class);
                             mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                             context.startActivity(mainActivity);
                             //Đóng Activity hiện tại
                             dismiss();
                      }else{
                             //Nếu đăng ký không thành công
                             registerErrorMsg.setText("Đăng ký không thành công");
                      }
               }
        } catch (JSONException e) {
               e.printStackTrace();
        }
 
	}

}
