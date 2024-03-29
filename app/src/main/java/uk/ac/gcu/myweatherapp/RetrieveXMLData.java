package uk.ac.gcu.myweatherapp;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

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
    // Message data bundle key to save xml parsed result.
    private static final String KEY_XML_PARSE_RESULT = "KEY_XML_PARSE_RESULT";
    private static final String ns = null;
    private int position;
    List<Day> days;
    String locationId;
    Location location;
    LocationRecyclerAdapter activity;
    LocationRecyclerAdapter.ViewHolder viewHolder;
    public RetrieveXMLData(int position, String locationID) {
        this.locationId = locationID;
        this.position = position;
        location = DataManager.getInstance().locations.get(position);
    }
    public RetrieveXMLData(Location location, LocationRecyclerAdapter.ViewHolder v, LocationRecyclerAdapter activity) {
        this.location = location;
        this.locationId = location.id;
        viewHolder = v;
        this.activity = activity;
//        this.position = position;
//        location = DataManager.getInstance().locations.get(position);
    }

    @Override
    protected Location doInBackground(Object[] objects) {
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
                        System.out.println("tag "+xpp.getName());
                        daysList.add(readItem(xpp));
                        System.out.println("first day "+daysList.get(0).description);
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
        return this.location;

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
            } else if (name.equalsIgnoreCase("description")) {
                String next = parser.nextText();
                List<String> attributes = Arrays.asList(next.split(","));
                Map<String,String> map = new HashMap<>();
                for (String att:attributes) {
                    String[] kv = att.split(":");
                    map.put(kv[0].trim(),kv[1].trim());
                }
                day.setDescription(map);
            }
//            TODO remove link
            else if (name.equals("link")) {
                day.setLink(parser.nextText());
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
                System.out.println("Country COde is "+this.location.countryCode);
//                parser.nextTag();
            } else if (name.equalsIgnoreCase("url")) {
                String iconUrl = parser.nextText();
                this.location.setIcon(iconUrl);
//                parser.nextTag();
            }
        }
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        activity.callBackData(viewHolder,location);
    }


    //    @Override
//    protected void onPostExecute(Location location) {
//        super.onPostExecute(location);
//        System.out.println("POST EXECUTION");
//        Message msg = new Message();
//        msg.what = MESSAGE_SHOW_XML_PARSE_RESULT;

        // Add error message in message object data.
//        Bundle bundle = new Bundle();
//        bundle.putString(KEY_XML_PARSE_RESULT, String.valueOf(location.position));
//        msg.setData(bundle);

        // Send message to activity ui update Handler.
//        showParseResultHandler.sendMessage(msg);
//        this.location.days.
//        for (Location loc:DataManager.getInstance().locations) {
//        System.out.println(this.location.getDays().get(0).toString());
//            for (Day d:this.location.days) {
//                System.out.println(d.toString());
//            }
//        }
//    }
}
