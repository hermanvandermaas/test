package com.mycompany.myapp;

import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import com.google.android.gms.location.places.*;
import com.google.android.gms.location.places.ui.*;
import com.google.android.gms.maps.*;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
	GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		gMap = googleMap;
		createPlaceSearch();
    }

	private void createPlaceSearch()
	{
		PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
			getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

		// Zoom naar gekozen Place
		autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener()
			{
				@Override
				public void onPlaceSelected(Place place)
				{
					gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(place.getViewport(), 0));
					//Toast.makeText(context, "Place: " + place.getName(), Toast.LENGTH_SHORT).show();
					//Log.i("HermLog", "Place: " + place.getName());
				}

				@Override
				public void onError(Status status)
				{
					Log.i("Info", "PlaceSelectionListener error: " + status);
				}
			});
	}
}
