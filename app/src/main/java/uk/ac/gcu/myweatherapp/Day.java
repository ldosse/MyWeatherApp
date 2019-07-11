package uk.ac.gcu.myweatherapp;

import java.util.ArrayList;
import java.util.Map;

public class Day{
    Map<String, String> description;
    //        String title;
    String link;
    String day;
    String brief;

    public Day() {

    }

//        public Day(String description, String title, String link) {
//                description = description;
//                title = title;
//                this.link = link;
//        }

    public String getDay() {
        return day;
    }

    public String getLink() {
        return link;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDescription(Map<String,String> description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        if (description == null)
            return "NA";
        return description.toString();
    }

    public void setBrief(String brief) {
        this.link = brief;
    }

    public String getBrief() {
        return link;
    }
}
