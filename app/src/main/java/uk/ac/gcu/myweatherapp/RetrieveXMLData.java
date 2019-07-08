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
import java.util.List;

public class RetrieveXMLData extends AsyncTask {
    private static final String ns = null;
    private final int position;
//    private Location location;
    List<Day> days;
//    = new ArrayList<>();
//    ArrayList<String> descriptions = new ArrayList();
    String loc;

    public RetrieveXMLData(int position, String loc) {
        this.loc = loc;
        this.position = position;
    }

    @Override
    protected List<Day> doInBackground(Object[] objects) {
        URL url = null;
        days = new ArrayList();
        InputStream in = null;
        String t = "";

        try {
            url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/"+ this.loc);
            System.out.println("URL is "+url);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openConnection().getInputStream(), "UTF_8");
            xpp.nextTag();
            int eventType = 0;
            eventType = xpp.getEventType();
            int d=-1;
            boolean go=false;
            while (eventType != XmlPullParser.END_DOCUMENT) {
//                System.out.println("XPP IS " + xpp.getName());
//                if (xpp != null){
//                descriptions.add(xpp.getText());
//                DataManager.getInstance().locations.get(position).temp+=xpp.getText();
//                System.out.println("\n\n\nqqqqqqqqqqqqqqqqqqqqqqqqqqq Get TEXT qqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//                System.out.println(xpp.getText());
//                System.out.println("\n\n\nqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        System.out.println("am i item "+xpp.getName());
                        d++;
                        this.days.add(new Day("desc", "title", "link"));
                    }
                    else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (d >= 0)
                            go=true;
//                            this.days.get(d).description = xpp.getText();
//                            t=xpp.getText();
//                          System.out.println("t linkk is "+t);
//                                getAttributeValue(null,"value");
                    }
//                    System.out.println("Start tag "+xpp.getName());
//                    if (xpp.getName()=="description"){
//                        tr=true;
//                    }
                } else if (eventType == XmlPullParser.END_TAG) {


                    System.out.println("End tag " + xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    System.out.println("I m text "+xpp.getText());
                    if (go)
                        t=xpp.getText();
                        System.out.println("t linkk is "+t);
                        go=false;
//                    if ( == "link") {
//                        this.days.get(d).link = xpp.getText();
//                        t=xpp.getText();
//                        System.out.println("t linkk is "+t);
//                    }
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
        DataManager.getInstance().locations.get(position).days.get(0).description = t ;

        System.out.println("End document");
        return days;
//        return descriptions;
        }
    List<Day> getDays(){
        return days;
    }



    protected void onPostExecute(List<Day> o) {
        super.onPostExecute(o);
//        LocationRecyclerAdapter.locationList.get(this.position).days = o;
//        DataManager.getInstance().locations.get(this.position).days=o;
//        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::SIZE\n\n\n "+o.size());
//        viewHolder.temp.setText(location.days.get(0).description);

    }


















//    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
//        List days = new ArrayList();
//
//        parser.require(XmlPullParser.START_TAG, ns, "rss");
//        while (parser.next() != XmlPullParser.END_TAG) {
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String name = parser.getName();
//            // Starts by looking for the item tag
//            if (name.equals("item")) {
//                days.add(readItem(parser));
//            } else {
//                skip(parser);
//            }
//        }
//        return days;
//    }




//    private Day readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "item");
//        String title = null;
//        String description = null;
//        String link = null;
//        while (parser.next() != XmlPullParser.END_TAG) {
//            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb  "+parser.getText());
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String name = parser.getName();
//            if (name.equals("title")) {
//                title = readTitle(parser);
//            } else if (name.equals("description")) {
//                description = readDescription(parser);
//            } else if (name.equals("link")) {
//                link = readLink(parser);
//            } else {
//                skip(parser);
//            }
//        }
//        return new Day(title, description, link);
//    }

//    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "link");
//        String link = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "link");
//        return link;
//    }
//
//    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "summary");
//        String description = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "summary");
//        return description;
//
//    }
//
//    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "title");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "title");
//        return title;
//    }

//    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
//        String result = "";
//        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbdddddddddddddddddddd  "+parser.getText());
//        if (parser.next() == XmlPullParser.TEXT) {
//            result = parser.getText();
//            parser.nextTag();
//        }
//        return result;
//    }

//    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
//        if (parser.getEventType() != XmlPullParser.START_TAG) {
//            throw new IllegalStateException();
//        }
//        int depth = 1;
//        while (depth != 0) {
//            switch (parser.next()) {
//                case XmlPullParser.END_TAG:
//                    depth--;
//                    break;
//                case XmlPullParser.START_TAG:
//                    depth++;
//                    break;
//            }
//        }
//    }

//        public InputStream getInputStream(URL url) {
//        try {
//            return url.openConnection().getInputStream();
//        } catch (IOException e) {
//            return null;
//        }
//    }

}
