package uk.ac.gcu.myweatherapp;

import java.util.ArrayList;
import java.util.Map;

public class Day{
        String day;
        String description;
        Map<String, String> attributes;
        String link;


        public Day() {
                this.day = "";
                this.description="";
                this.link="";
        }

        public String getDay() {
                return day;
        }

        public void setDay(String day) {
                this.day = day;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getAttributes() {
                if (attributes == null)
                        return "NA";
                return attributes.toString();
        }

        public void setAttributes(Map<String,String> description) {
                this.attributes = description;
        }

        public String getLink() {
                return link;
        }

        public void setLink(String link) {
                this.link = link;
        }

}