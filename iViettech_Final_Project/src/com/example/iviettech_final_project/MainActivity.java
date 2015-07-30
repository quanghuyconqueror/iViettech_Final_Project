package com.example.iviettech_final_project;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
        
        //create tab host
        TabHost tabHost = getTabHost();
        
        //tab for show
        TabSpec showSpec = tabHost.newTabSpec("Show");
        showSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_dulich_diadanh));
        Intent showIntent = new Intent(this, ShowActivity.class);
        showSpec.setContent(showIntent);
        
        //tab for search
        TabSpec searchSpec = tabHost.newTabSpec("Search");
        searchSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_dulich_khudulich));
        Intent searchIntent = new Intent(this, SearchActivity.class);
        searchSpec.setContent(searchIntent);
        
        //tab for order
        TabSpec orderSpec = tabHost.newTabSpec("Order");
        orderSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_dulich_ticket));
        Intent orderIntent = new Intent(this, OrderActivity.class);
        orderSpec.setContent(orderIntent);
        
        //tab for map
        TabSpec mapSpec = tabHost.newTabSpec("Map");
        mapSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_entertaiment));
        Intent mapIntent = new Intent(this, MapActivity.class);
        mapSpec.setContent(mapIntent);
        
        //tab for category
        TabSpec categorySpec = tabHost.newTabSpec("Category");
        categorySpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_giaitri_cinema));
        Intent categoryIntent = new Intent(this, CategoryActivity.class);
        categorySpec.setContent(categoryIntent);
        
        //tab for login
        TabSpec loginSpec = tabHost.newTabSpec("Login");
        loginSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_home_giaitri_congvien));
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginSpec.setContent(loginIntent);
        
        //add tab to tabhost
        tabHost.addTab(showSpec);
        tabHost.addTab(searchSpec);
        tabHost.addTab(orderSpec);
        tabHost.addTab(mapSpec);
        tabHost.addTab(categorySpec);
        tabHost.addTab(loginSpec);
   

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
