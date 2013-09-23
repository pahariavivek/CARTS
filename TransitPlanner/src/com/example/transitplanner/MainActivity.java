package com.example.transitplanner;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.os.Debug;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, BUSTOPS);
        AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        textView1.setAdapter(adapter);
        AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        textView2.setAdapter(adapter);
        
        setUpMapIfNeeded();
        Log.i("just","sdafasdfasdfas");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private static final String[] BUSTOPS = new String[] {
        "Belgium", "basd", "badfg", "bdefg", "bdggg", "France", "Italy", "Germany", "Spain"
    };
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
    
    public void showSearchBack(View view){
    	LinearLayout map_container = (LinearLayout) findViewById(R.id.map_container);
    	map_container.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 5));
    	((Button) view).setVisibility(View.GONE);
    }
    public void getPlan(View view){
    	LinearLayout map_container = (LinearLayout) findViewById(R.id.map_container);
    	map_container.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 0));
    	Button searchBack = (Button) findViewById(R.id.search_back);
    	searchBack.setVisibility(View.VISIBLE);
    	//Intent intent = new Intent(this, PlanActivity.class);
    	//startActivity(intent);
    }
    public void showMoreOptions(View view){
    	boolean on = ((ToggleButton) view).isChecked();
    	LinearLayout moreOptions = (LinearLayout) findViewById(R.id.more_options);
    	Log.i("height", Integer.toString(moreOptions.getMeasuredHeight()));
        if (on) {
        	moreOptions.setVisibility(android.view.View.VISIBLE);
        } else {
        	moreOptions.setVisibility(android.view.View.GONE);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
