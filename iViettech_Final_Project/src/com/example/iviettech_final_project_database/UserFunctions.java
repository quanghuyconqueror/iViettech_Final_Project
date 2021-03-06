package com.example.iviettech_final_project_database;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserFunctions {

	private JSONParser jsonParser;
	
	private final static String TAG_NAME = "UserFunctions";

	private static String loginURL = "http://enddev.site50.net/iViettechFinalProject/";
	private static String registerURL = "http://enddev.site50.net/iViettechFinalProject/";


	private static String login_tag = "login";
	private static String register_tag = "register";
	private static String search_city_tag = "get_restaurant_by_city";

	// Hàm xây dựng khởi tạo đối tượng
	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	// Thực hiện công việc đăng nhập với email và password
	public JSONObject loginUser(String username, String password) {
		// Xây dựng các giá trị
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// Trả về đối tượng là 1 JSONObject
		return json;
	}
	
	public ArrayList<JSONObject> searchRestaurantByCity(String city) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", search_city_tag));
		params.add(new BasicNameValuePair("city", city));
		ArrayList<JSONObject> jsonArray = jsonParser.getJSONArrayFromUrl(loginURL, params);
		// Trả về đối tượng là 1 JSONObject
		return jsonArray;
	}

	public JSONObject registerUser(String username, String password,
			String email, String firstname, String lastname,
			String city, int gender, int age, String avatar) {
		// Xây dựng các giá trị
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("firstname", firstname));
		params.add(new BasicNameValuePair("lastname", lastname));
		params.add(new BasicNameValuePair("city", city));
		params.add(new BasicNameValuePair("gender", "" + gender));
		params.add(new BasicNameValuePair("age", "" + age));
		params.add(new BasicNameValuePair("avatar", avatar));
		
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		Log.i(TAG_NAME, "Lay Json thanh cong");
		// Trả về đối tượng là 1 JSONObject
		return json;
	}

	// Kiểm tra người dùng
	public boolean isUserLoggedIn(Context context) {
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if (count > 0) {
			return true;
		}
		return false;
	}

	// Thực hiện việc logoutUser bằng cách xóa dữ liệu database
	public boolean logoutUser(Context context) {
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTableUser();
		return true;
	}

}
