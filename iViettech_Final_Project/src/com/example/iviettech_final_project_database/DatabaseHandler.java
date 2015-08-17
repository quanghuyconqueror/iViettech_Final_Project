package com.example.iviettech_final_project_database;

import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
   	//Phiên bản Database (Version)
   	private static final int DATABASE_VERSION = 1;
   	
   	//Tên Database
   	private static final String DATABASE_NAME = "iViettechFinalProject";
 
   	//Tên table đăng nhập
   	private static final String TABLE_NAME = "Users";
 
   	// Login Table Columns names
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
 
   	public DatabaseHandler(Context context) {
          	super(context, DATABASE_NAME, null, DATABASE_VERSION);
   	}
 
   	//Khởi tạo table
   	@Override
   	public void onCreate(SQLiteDatabase db) {
          	String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                       	+ KEY_ID + " INTEGER PRIMARY KEY,"
                       	+ KEY_USERNAME + " TEXT UNIQUE,"
                       	+ KEY_PASSWORD + " TEXT,"
                       	+ KEY_EMAIL + " TEXT,"
                       	+ KEY_FIRSTNAME + " TEXT,"
                       	+ KEY_LASTNAME + " TEXT,"
                       	+ KEY_CITY + " TEXT,"
                       	+ KEY_GENDER + " INTEGER,"
                       	+ KEY_AGE + " INTEGER,"
                       	+ KEY_AVATAR + " TEXT" + ")";
          	db.execSQL(CREATE_LOGIN_TABLE);
   	}
 
   	//Cập nhật database
   	@Override
   	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          	//Xóa table cũ
          	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
          	//Khởi tạo lại database
          	onCreate(db);
   	}
 
   	//Lưu trữ thông tin chi tiết người dùng vào database
   	public void addUser(String username, String password, String email, String firstname, String lastname, String city, int gender, int age, String avatar) {
          	
			SQLiteDatabase db = this.getWritableDatabase();
          	ContentValues values = new ContentValues();
          	values.put(KEY_USERNAME, username);
          	values.put(KEY_PASSWORD, password);
          	values.put(KEY_EMAIL, email);
          	values.put(KEY_FIRSTNAME, firstname);
          	values.put(KEY_LASTNAME, lastname);
			values.put(KEY_CITY, city);
			values.put(KEY_GENDER, gender);
			values.put(KEY_AGE, age);
			values.put(KEY_AVATAR, avatar);
          	db.insert(TABLE_NAME, null, values);
			
          	db.close();
   	}
   	
   	//Lấy dữ liệu người dùng trong database
   	public HashMap<String, String> getUserDetails(){
          	HashMap<String,String> user = new HashMap<String,String>();
          	String selectQuery = "SELECT  * FROM " + TABLE_NAME;     	
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Di chuyển tới row đầu tiên
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
          	user.put("Username", cursor.getString(1));
          	user.put("Password", cursor.getString(2));
          	user.put("Email", cursor.getString(3));
          	user.put("Firstname", cursor.getString(4));
			user.put("Lastname", cursor.getString(5));
			user.put("City", cursor.getString(6));
			user.put("Gender", "" + cursor.getInt(7));
			user.put("Age", "" + cursor.getInt(8));
			user.put("Avatar", cursor.getString(9));
        }
        cursor.close();
        db.close();
          	return user;
   	}
 
   	//Xem trạng thái đăng nhập, trả về số row trong table
   	public int getRowCount() {
          	String countQuery = "SELECT  * FROM " + TABLE_NAME;
          	SQLiteDatabase db = this.getReadableDatabase();
          	Cursor cursor = db.rawQuery(countQuery, null);
          	int rowCount = cursor.getCount();
          	db.close();
          	cursor.close();
          	return rowCount;
   	}
   	
   	//Thực hiện xóa tất cả row trong table
   	public void resetTables(){
          	SQLiteDatabase db = this.getWritableDatabase();
          	db.delete(TABLE_NAME, null, null);
          	db.close();
   	}
 
}
