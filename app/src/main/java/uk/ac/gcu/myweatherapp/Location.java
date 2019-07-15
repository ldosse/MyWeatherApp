package uk.ac.gcu.myweatherapp;


import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class Location {
    static int pos=0;
    String name;
    String id;
//    String temp;
    int position;
    String icon;
    String countryCode;
    int img;

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

    public Location(String name, String id, int img) {
        this.name = name;
        this.id = id;
//        this.temp=null;
//        this.position=pos;
        this.img=img;
    }
//
//    public void setTemp(String temp) {
////        this.temp = temp;
//    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void setImg(int img){
        this.img = img;
    }
}
