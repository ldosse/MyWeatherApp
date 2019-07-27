package uk.ac.gcu.myweatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import uk.ac.gcu.myweatherapp.Models.Location;
import uk.ac.gcu.myweatherapp.settings.SettingsActivity;


public class ActivityLocationList extends AppCompatActivity {
    private List<Location> locationList;
    private LocationRecyclerAdapter recyclerAdapter;
    DataManager dm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        initializeDisplayContent();
        dm = DataManager.getInstance();
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }

    private void initializeDisplayContent() {
        final RecyclerView recyclerLocations = (RecyclerView) findViewById(R.id.RecyclerViewLocations);
        final LinearLayoutManager locationsLayoutManager = new LinearLayoutManager(this);
        recyclerLocations.setLayoutManager(locationsLayoutManager);
//        locationList = dm.locations;
//        for (Location loc:dm.locations) {
//            RetrieveXMLData getXML = new RetrieveXMLData(loc);
//            getXML.execute();
//            loc = getXML.location;
////            days = getXML.getDays();
//        }
        recyclerAdapter = new LocationRecyclerAdapter(this);
        recyclerLocations.setAdapter(recyclerAdapter);
        android.support.v7.preference.PreferenceManager
                .setDefaultValues(this, R.xml.preferences, false);

        SharedPreferences sharedPref =
                android.support.v7.preference.PreferenceManager
                        .getDefaultSharedPreferences(this);
        Boolean switchPref = sharedPref.getBoolean
                (SettingsActivity.KEY_PREF_UPDATES_SWITCH, false);
        Toast.makeText(this, switchPref.toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void setBackgroundAlarm(){
//        SharedPreferences sharedPref =
//                android.support.v7.preference.PreferenceManager
//                        .getDefaultSharedPreferences(this);
//        Boolean switchPref = sharedPref.getBoolean
//                (SettingsActivity.KEY_PREF_UPDATES_SWITCH, false);
//        if (switchPref)
////            new
//            System.out.println("SET BACKGROUND TASK");
//
//    }
}