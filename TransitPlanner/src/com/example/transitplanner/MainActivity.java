package com.example.transitplanner;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.os.Debug;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TimePicker;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements MoreOptionsDialogFragment.MoreOptionsDialogListener {
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.more_options:
            	showMoreOptions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
 
    public String prepareQuery(View view){
    	return "http://www.transittripplanner.co.in/TransitTripPlanner/controller_s?target=plan&srcStopNo=1121&destStopNo=22&hr=8&min=30&sec=0&ampm=am&height=4&stend=start&walk=500&mode=all&network=static&cache=0.3225696527604375";
    }
 
    public void getPlan(View view){
    	LinearLayout map_container = (LinearLayout) findViewById(R.id.map_container);
    	map_container.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 0));
    	Button searchBack = (Button) findViewById(R.id.search_back);
    	searchBack.setVisibility(View.VISIBLE);
       	String query = prepareQuery(view);
    	new QueryServer().execute(query);
    	//Intent intent = new Intent(this, PlanActivity.class);
    	//startActivity(intent);
    }

    public void showMoreOptions(){
    	DialogFragment dialog = new MoreOptionsDialogFragment();
        dialog.show(getFragmentManager(), "MoreOptionsDialogFragment");
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }

    
}
