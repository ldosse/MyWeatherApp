package uk.ac.gcu.myweatherapp;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class DataManager {
    private static DataManager instance;
    public List<Location> locations;

    public static DataManager getInstance(){
        if(instance==null){
            instance = new DataManager();
        }
        return instance;
    }

    public DataManager() {
        this.locations = new LinkedList<>();
        initializeData();
//        updateData();
    }

    private void initializeData(){
        Location glasgow = new Location("Glasgow","2648579");
        Location london = new Location("London","2643743");
        Location newYork = new Location("NewYork", "5128581");
        Location oman = new Location("Oman","287286");
        Location mauritius = new Location("Mauritius","934154");
        Location bangladesh = new Location("Bangladesh","1185241");
        Location rabat = new Location("Rabat","2538475");
        locations.add(glasgow);
        locations.add(london);
        locations.add(newYork);
        locations.add(oman);
        locations.add(mauritius);
        locations.add(bangladesh);
        locations.add(rabat);
    }

//    private void updateData(){
//        for (Location loc:locations ) {
//            loc.updateData();
//        }
//    }
}
