package uk.ac.gcu.myweatherapp;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetrieveXMLData extends AsyncTask {
    private static final String ns = null;
    private int position;
    List<Day> days;
    String locationId;
    Location location;

    public RetrieveXMLData(int position, String locationID) {
        this.locationId = locationID;
        this.position = position;
        location = DataManager.getInstance().locations.get(position);
    }
    public RetrieveXMLData(Location location) {
        this.location = location;
        this.locationId = location.id;
//        this.position = position;
//        location = DataManager.getInstance().locations.get(position);
    }

    @Override
    protected List<Day> doInBackground(Object[] objects) {
        URL url = null;
        InputStream in = null;
        String t = "";
        List<Day> daysList = new ArrayList<>();
        try {
            url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/"+ this.locationId);
            System.out.println("URL is " + url);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openConnection().getInputStream(), "UTF_8");

            int eventType = 0;
            eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("image")) {
                        readImage(xpp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("item")) {
                        daysList.add(readItem(xpp));
                    }
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.location.setDays(daysList);
//        DataManager.getInstance().locations.get(position).days = this.days ;
        System.out.println("End document");
        System.out.println(this.location.name);
        for (Day d:this.location.getDays()){
            System.out.println(d.getBrief());
        }
        return this.location.getDays();

    }

    List<Day> getDays(){
        return days;
    }

    private Day readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        Day day = new Day();
        String title = null;
        String link = null;

        parser.require(XmlPullParser.START_TAG, ns, "item");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
//                TODO initialize next outside if statements
                String next = parser.nextText();
                int firstCol = next.indexOf(':');
                day.setDay(next.substring(0, firstCol).trim());
                day.setBrief(next.substring(next.indexOf(':')+1,next.indexOf(',')).trim());
            } else if (name.equals("description")) {
                String next = parser.nextText();
                List<String> description = Arrays.asList(next.split(","));
                Map<String,String> map = new HashMap<>();
                    for (String att:description) {
                    String[] kv = att.split(":");
                    map.put(kv[0].trim(),kv[1].trim());
                }
                 day.setDescription(map);
            }
        }
        return day;
    }

    private void readImage(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "image");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                System.out.println("Next tag image");
                String[] str = parser.nextText().split(",");
                this.location.setName(str[0].replace("BBC Weather - Forecast for","").trim());
                this.location.setCountryCode(str[1].trim());
//                parser.nextTag();
            } else if (name.equalsIgnoreCase("url")) {
                String iconUrl = parser.nextText();
                this.location.setIcon(iconUrl);

                parser.nextTag();
            }
        }
    }



}
