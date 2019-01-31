package com.example.wait_person;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.app.Activity;

public class Place2 extends Activity {

	// position of markers
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);

	// variable map
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place2);

		// get the map and put into a variable
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// place 2 markers
		if (map != null) {
			Marker hamburg = map.addMarker(new MarkerOptions()
					.position(HAMBURG).title("Hamburg"));
			Marker kiel = map.addMarker(new MarkerOptions()
					.position(KIEL)
					.title("Kiel")
					.snippet("Kiel is cool")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);
		return true;
	}

}
