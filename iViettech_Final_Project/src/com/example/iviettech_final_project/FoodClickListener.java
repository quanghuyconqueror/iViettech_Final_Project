package com.example.iviettech_final_project;

import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;

public class FoodClickListener implements OnClickListener {
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		
		case R.id.iv_main:
			Intent mainIntent = new Intent(v.getContext(), MainActivity.class);
			v.getContext().startActivity(mainIntent);
			break;
			
		case R.id.iv_map:
			Intent mapIntent = new Intent(v.getContext(), MapActivity.class);
			v.getContext().startActivity(mapIntent);
			break;
			
		case R.id.iv_search:
			Intent searchIntent = new Intent(v.getContext(), SearchActivity.class);
			v.getContext().startActivity(searchIntent);
			break;
			
		case R.id.iv_notification:
			Intent notificationIntent = new Intent(v.getContext(), NotificationActivity.class);
			v.getContext().startActivity(notificationIntent);
			break;
			
		case R.id.iv_category:
			Intent categoryIntent = new Intent(v.getContext(), CategoryActivity.class);
			v.getContext().startActivity(categoryIntent);
			break;
			
		case R.id.iv_login:
			Intent loginIntent = new Intent(v.getContext(), LoginActivity.class);
			v.getContext().startActivity(loginIntent);
			break;
			
		default:
			break;
		}
	}

}
