package uk.ac.gcu.myweatherapp;

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
    private final int position;
    List<Day> days;
    String locationId;

    public RetrieveXMLData(int position, String locationID) {
        this.locationId = locationID;
        this.position = position;
    }

    @Override
    protected List<Day> doInBackground(Object[] objects) {
        URL url = null;
        InputStream in = null;
        String t = "";
        days = new ArrayList<>();
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
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        this.days.add(readItem(xpp));
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
        DataManager.getInstance().locations.get(position).days = this.days ;
        System.out.println("End document");
        for (Day d:this.days){
            System.out.println(d.getDescription());
        }
        return this.days;

    }

    List<Day> getDays(){
        return days;
    }


    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    protected void onPostExecute(List<Day> o) {
        super.onPostExecute(o);
    }



    private Day readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        Day day = new Day();
        String title = null;
        Map<String, String> attributes;
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
                day.setDay(next.substring(0, firstCol));
                day.setBrief(next.substring(next.indexOf(':')+1,next.indexOf(',')));
            } else if (name.equals("description")) {
                String next = parser.nextText();
                List<String> description = Arrays.asList(next.split(","));
                Map<String,String> map = new HashMap<>();
                    for (String att:description) {
                    String[] kv = att.split(":");
                    map.put(kv[0],kv[1]);
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

}
