package uk.ac.gcu.myweatherapp.Models;


import java.util.ArrayList;
import java.util.List;

public class Location {
    static int pos=0;
    private String name;
    private String id;
    int position;
    private String icon;
    private String countryCode;
    private List<Day> days = new ArrayList<>();

    public int getCountryImg() {
        return countryImg;
    }

    int countryImg;

    public List<Day> getDays() {
        return days;
    }


    public Location(String name, String id) {
        this.name = name;
        this.id = id;
//        for(int i=0; i<3; i++)
//            days.add(new Day("","",""));
        this.position = pos++;
    }

    public Location(String name, String id, int countryImg) {
        this.name = name;
        this.id = id;
//        this.temp=null;
//        this.position=pos;
        this.countryImg = countryImg;
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

    public void setCountryImg(int countryImg){
        this.countryImg = countryImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
