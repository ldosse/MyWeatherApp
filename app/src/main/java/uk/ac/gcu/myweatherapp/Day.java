package uk.ac.gcu.myweatherapp;

import java.util.ArrayList;
import java.util.Map;

public class Day{
    public Map<String, String> getDescription() {
        return description;
    }

    Map<String, String> description;
    String link;
    String day;
    String brief;

    public String toString() {
        if (this.description==null)
            return "";
        return description.toString();
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }



}
