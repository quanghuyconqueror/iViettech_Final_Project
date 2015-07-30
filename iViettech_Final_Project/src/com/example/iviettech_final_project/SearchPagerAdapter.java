package com.example.iviettech_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SearchPagerAdapter extends android.support.v4.view.PagerAdapter{

	@Override
	public int getCount() {
		return 2;
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
			resID = R.layout.search_suggest;
			break;
			
		case 1:
			resID = R.layout.search_found;
			break;
		}
		
		View view = li.inflate(resID, null);
	
		container.addView(view, 0);
		return view;
	}
	
	//destroy view don't display
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
	

}
