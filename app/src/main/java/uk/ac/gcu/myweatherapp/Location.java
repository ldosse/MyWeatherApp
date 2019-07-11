package uk.ac.gcu.myweatherapp;


import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class Location {
    static int pos=0;
    String name;
    String id;
    String temp;
    int position;

    public List<Day> getDays() {
        return days;
    }

    List<Day> days = new ArrayList<>();

    public Location(String name, String id) {
        this.name = name;
        this.id = id;
//        for(int i=0; i<3; i++)
//            days.add(new Day("","",""));
        this.position = pos++;
    }

    public Location(String name, String id, int pos) {
        this.name = name;
        this.id = id;
        this.temp=null;
        this.position=pos;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void updateData(){
//        RetrieveXMLData xmlData = new RetrieveXMLData(id, position);
//        RetrieveXMLData xmlData = new RetrieveXMLData(this.position, this.id);
//        RetrieveXMLData getXML = new RetrieveXMLData(location.id,position);
//        AsyncTask execute = xmlData.execute();
//        System.out.println("THIS LOCATION link IIIS "+this.days.get(0).link);
//        return true;
    }
}
