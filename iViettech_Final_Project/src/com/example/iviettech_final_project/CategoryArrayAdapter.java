package com.example.iviettech_final_project;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryArrayAdapter extends ArrayAdapter<CategoryItem> {
	
	private int resourceID;
	public CategoryArrayAdapter(Context context, int resource,
			List<CategoryItem> objects) {
		super(context, resource, objects);
		this.resourceID = resource;	
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		CategoryItem categoryItem = getItem(position);
		View row = convertView;
		
		if (row == null) {
			LayoutInflater inflater;
			inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(resourceID, null);
		}
		else {
			row = convertView;
		}
		
		TextView tvNameItem = (TextView) row.findViewById(R.id.tv_category_item);
		ImageView ivImageID = (ImageView) row.findViewById(R.id.iv_category_item);
		
		tvNameItem.setText(categoryItem.getNameItem());
		ivImageID.setImageResource(categoryItem.getImageID());
		
		return row;
	}
}
