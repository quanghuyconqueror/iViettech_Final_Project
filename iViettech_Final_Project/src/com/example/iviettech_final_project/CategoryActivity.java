package com.example.iviettech_final_project;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.Build;

public class CategoryActivity extends Activity implements OnItemClickListener {
	
	
	private ListView lvCategory;
	private CategoryArrayAdapter adapterCategory;
	
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_category);
		
        
        lvCategory = (ListView) findViewById(R.id.lv_category);
        
        ArrayList<CategoryItem> itemArray = new ArrayList<CategoryItem>();
        itemArray.add(new CategoryItem("Bộ sưu tập", R.drawable.ic_home_dulich_ticket));
        itemArray.add(new CategoryItem("Khuyến mãi", R.drawable.ic_home_giaitri_cinema));
        itemArray.add(new CategoryItem("Xem gần đây", R.drawable.ic_launcher));
        itemArray.add(new CategoryItem("Thêm địa điểm", R.drawable.ic_home_entertaiment));
        itemArray.add(new CategoryItem("Cài đặt", R.drawable.ic_home_dulich_ticket));
        itemArray.add(new CategoryItem("Giới thiệu", R.drawable.ic_home_dulich_khudulich));
        
        adapterCategory = new CategoryArrayAdapter(this, R.layout.category_listview, itemArray);
        lvCategory.setAdapter(adapterCategory);
        lvCategory.setOnItemClickListener(this);
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		
	}

	

}
