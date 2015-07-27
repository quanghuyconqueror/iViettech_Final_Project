package com.example.iviettech_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainPagerAdapter extends android.support.v4.view.PagerAdapter {

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (View) arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater li = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int resID = 0;
		switch (position) {
		case 0:
			resID = R.layout.main_highlight;
			break;

		case 1:
			resID = R.layout.main_new;
			break;
			
		case 2:
			resID = R.layout.main_collection;
			break;
		}
		
		View view = li.inflate(resID, null);
		ImageView m_ivMain, m_ivMap, m_ivSearch, m_ivNotification;
		ImageView m_ivCategory, m_ivLogin;
		m_ivMain = (ImageView) view.findViewById(R.id.iv_main);
		m_ivMap = (ImageView) view.findViewById(R.id.iv_map);
		m_ivSearch = (ImageView) view.findViewById(R.id.iv_search);
		m_ivNotification = (ImageView) view.findViewById(R.id.iv_notification);
		m_ivCategory = (ImageView) view.findViewById(R.id.iv_category);
		m_ivLogin = (ImageView) view.findViewById(R.id.iv_login);

		m_ivMain.setOnClickListener(new FoodClickListener());
		m_ivMap.setOnClickListener(new FoodClickListener());
		m_ivSearch.setOnClickListener(new FoodClickListener());
		m_ivNotification.setOnClickListener(new FoodClickListener());
		m_ivCategory.setOnClickListener(new FoodClickListener());
		m_ivLogin.setOnClickListener(new FoodClickListener());
		container.addView(view, 0);
		return view;

		
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
	
}
