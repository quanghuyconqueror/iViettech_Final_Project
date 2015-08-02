package com.example.iviettech_final_project.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlOpenHelper extends SQLiteOpenHelper {
	
	public SqlOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}
	
	public static final String DATABASE_NAME = "KFood";
	public static final int VERSION = 1;
	
	public static final String TABLE_NAME = "Restaurant";
	
	public static final String ID = "Id";
	public static final String NAME_RESTAURANT = "NameRestaurant";
	public static final String ADDRESS = "Address";
	public static final String CITY = "City";
	public static final String DETAIL = "Detail";
	public static final String LIKE_NUMBER = "LikeNumber";
	public static final String DISLIKE_NUMBER = "DislikeNumber";
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		createDatabase(db);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void createDatabase(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + "(" + ID + " integer primary key autoincrement not null, "
												+ NAME_RESTAURANT + " text "
												+ ADDRESS + " text "
												+ CITY + " text "
												+ DETAIL + " text "
												+ LIKE_NUMBER + " integer "
												+ DISLIKE_NUMBER + " integer "
												+ ");"
												);
	}
}
