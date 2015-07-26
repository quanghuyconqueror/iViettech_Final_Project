package com.example.iviettech_final_project;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Build;

public class LoginActivity extends Activity {
	ImageView m_ivMain, m_ivMap, m_ivSearch, m_ivNotification;
	ImageView m_ivCategory, m_ivLogin;
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		m_ivMain = (ImageView) findViewById(R.id.iv_main);
        m_ivMap = (ImageView) findViewById(R.id.iv_map);
        m_ivSearch = (ImageView) findViewById(R.id.iv_search);
        m_ivNotification = (ImageView) findViewById(R.id.iv_notification);
        m_ivCategory = (ImageView) findViewById(R.id.iv_category);
        m_ivLogin = (ImageView) findViewById(R.id.iv_login);
        
        m_ivMain.setOnClickListener(new FoodClickListener());
        m_ivMap.setOnClickListener(new FoodClickListener());
        m_ivSearch.setOnClickListener(new FoodClickListener());
        m_ivNotification.setOnClickListener(new FoodClickListener());
        m_ivCategory.setOnClickListener(new FoodClickListener());
        m_ivLogin.setOnClickListener(new FoodClickListener());

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



}
