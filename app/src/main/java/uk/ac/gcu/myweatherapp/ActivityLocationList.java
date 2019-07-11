package uk.ac.gcu.myweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;


public class ActivityLocationList extends AppCompatActivity {
    private List<Location> locationList;
    private LocationRecyclerAdapter recyclerAdapter;
    DataManager dm = DataManager.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        final RecyclerView recyclerLocations = (RecyclerView) findViewById(R.id.RecyclerViewLocations);
        final LinearLayoutManager locationsLayoutManager = new LinearLayoutManager(this);
        recyclerLocations.setLayoutManager(locationsLayoutManager);
        locationList = dm.locations;
        for (Location loc:locationList) {
            RetrieveXMLData getXML = new RetrieveXMLData(loc);
            getXML.execute();
//            days = getXML.getDays();
        }
        recyclerAdapter = new LocationRecyclerAdapter(this,locationList);
        recyclerLocations.setAdapter(recyclerAdapter);
    }


}