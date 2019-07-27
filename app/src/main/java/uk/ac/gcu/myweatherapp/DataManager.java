package uk.ac.gcu.myweatherapp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import uk.ac.gcu.myweatherapp.Models.Location;

public class DataManager {
    private static DataManager instance;
    public static Boolean updated=false;
    public List<Location> locations;
    public Map<String,Integer> iconsMap;
    public static int size;

    public static DataManager getInstance(){
        if(instance==null){
            instance = new DataManager();
        }
        return instance;
    }

    public DataManager() {
        this.locations = new LinkedList<>();
        initializeData();
        createIconsMap();
//        updateData();
    }

    private void initializeData(){
        Location glasgow = new Location("Glasgow","2648579", R.drawable.glasgow_main);
        Location london = new Location("London","2643743", R.drawable.london);
        Location newYork = new Location("NewYork", "5128581",R.drawable.new_york_city);
        Location oman = new Location("Oman","287286",R.drawable.muscat);
        Location mauritius = new Location("Mauritius","934154", R.drawable.portlouis);
        Location bangladesh = new Location("Bangladesh","1185241",R.drawable.dhaka);
        Location rabat = new Location("Rabat","2538475",R.drawable.rabat);
        locations.add(glasgow);
        locations.add(london);
        locations.add(newYork);
        locations.add(oman);
        locations.add(mauritius);
        locations.add(bangladesh);
        locations.add(rabat);
    }

    public void createIconsMap(){
        this.iconsMap = new HashMap<>();
        String[] s = {
                "sunny day",
                "partly cloudy (night)",
                "sunny intervals",
                "white medium level cloud",
                "black low level cloud",
                "light rain shower (night)",
                "light rain shower",
                "drizzle",
                "light rain",
                "heavy rain shower (night)",
                "heavy rain shower",
                "heavy rain",
                "sleet shower (night)",
                "sleet shower",
                "cloudy with sleet",
                "hail shower (night)",
                "hail shower",
                "cloudy with hail",
                "light snow shower (night)",
                "light snow shower",
                "cloudy with light snow",
                "heavy snow shower (night)",
                "heavy snow shower",
                "cloudy with heavy snow",
                "thundery shower (night)",
                "thundery shower",
                "thunderstorms"};

        for(int i=1; i<s.length+1; i++){
            this.iconsMap.put(s[i-1],i);
        }
    }

    public int getNext(int position){
        return (position+1) % this.locations.size();
    }

    public int getPrevious(int position){
            if (position==0)
                return this.locations.size()-1;
            return (position-1) % this.locations.size();
    }

    public void updateData(){
        System.out.println("updating data manager");
        for(int i=0; i<this.locations.size(); i++){
            RetrieveXMLData getXML = new RetrieveXMLData(i);
            getXML.execute();
        }
//        TODO REMOVE UPDATED=TRUE BECAUSE REDUNDANT CHECK FILE RETRIEVEXML POSTEXECUTE
        updated=true;
    }

}
