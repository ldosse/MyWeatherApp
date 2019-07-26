package uk.ac.gcu.myweatherapp.Models;

import java.util.Map;

public class Day{
    public Map<String, String> getDescription() {
        return description;
    }

    private Map<String, String> description;
    private String link;
    private String day;
    private String brief;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    String icon;

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
