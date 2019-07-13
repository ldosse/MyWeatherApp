package uk.ac.gcu.myweatherapp;

import java.util.ArrayList;
import java.util.List;

public class Location {
    static int POSITION=0;
    String name;

    String id;
    int position;
    String countryCode;
    List<Day> days = new ArrayList<>();
    String iconURL;

    public Location(String name, String id) {
        this.name = name;
        this.id = id;
        this.days.add(new Day());
//        for(int i=0; i<3; i++)
//            days.add(new Day("","",""));
        this.position = POSITION++;
    }

    public Location(String name, String id, int pos) {
        this.name = name;
        this.id = id;
        this.position=pos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Day> getDays() {
        return days;
    }

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

    public void setDays(List<Day> days) {
        this.days = days;
    }
    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}