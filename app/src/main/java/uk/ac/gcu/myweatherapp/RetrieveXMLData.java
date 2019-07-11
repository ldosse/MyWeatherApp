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
//    private final
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
                        xpp.nextTag();
                        if( xpp.getName() == "title"){
                            System.out.println("Next tag image");
                            String[] str = xpp.getText().split(",");
                            this.location.setName(str[0].replace("BBC Weather - Forecast for","").trim());
                            this.location.setCountryCode(str[1].trim());
                            xpp.nextTag();
                        }
                        if(xpp.getName() == "url"){
                            String iconUrl = xpp.getText();
                            this.location.setDrawable(LoadImageFromWebOperations(iconUrl));
                            this.location.setIcon(iconUrl);

                            xpp.nextTag();
                        }
//                        String title = readText(xpp);
//                        readTitleTag(xpp);
//                        this.days.add(readItem(xpp));
                    }
                    else if (xpp.getName().equalsIgnoreCase("item")) {
//                        this.location.getDays().add(readItem(xpp));
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
        for (Day d:this.location.getDays()){
            System.out.println(d.getDescription());
        }
        return this.location.getDays();

    }

//    private String processTitle(String string){
//        String[] str = string.split(",");
//        locationName = str[0].replace("BBC Weather - Forecast for","").trim();
//        countryCode = str[1].trim();
//        return "";
//    }
    private void readTitleTag(XmlPullParser parser) {
        parser.getText();
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

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "BBC");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
