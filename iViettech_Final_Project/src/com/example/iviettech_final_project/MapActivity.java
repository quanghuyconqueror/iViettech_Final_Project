package com.example.iviettech_final_project;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MapActivity extends Activity implements OnMarkerClickListener {
	GoogleMap googleMap;
	ProgressDialog loadDialog;
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        loadDialog = new ProgressDialog(this);
        loadDialog.setTitle("Đang tải Map ...");
        loadDialog.setMessage("Vui lòng đợi ... ");
        loadDialog.setCancelable(true);
        loadDialog.show();
        try {
        	
        	
        	if (googleMap == null) {
        		googleMap = ((MapFragment) getFragmentManager().
        				findFragmentById(R.id.map_google)).getMap();
        		googleMap.setOnMapLoadedCallback(new OnMapLoadedCallback() {
					
					@Override
					public void onMapLoaded() {
						loadDialog.dismiss();
						
					}
				});
        		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        		googleMap.setMyLocationEnabled(true);
        		googleMap.getUiSettings().setZoomControlsEnabled(true);
        		LatLng test = new LatLng(16.073445, 108.149839);
        		MarkerOptions option = new MarkerOptions();
        		option.position(test);
        		option.title("Quán mì sinh viên").snippet("This is a cool");
        		option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        		option.alpha(0.8f);
        		option.rotation(90);	
        		Marker marker = googleMap.addMarker(option);
        		googleMap.setOnMarkerClickListener(this);
        	}
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        
     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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
	public boolean onMarkerClick(Marker marker) {
		marker.showInfoWindow();
		return false;
	}

	private void moveCameraToCurrentPosition() {
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
		if (location == null) {
			
		}
		
	}

}
