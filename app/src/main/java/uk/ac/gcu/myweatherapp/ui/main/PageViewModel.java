package uk.ac.gcu.myweatherapp.ui.main;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.Map;

import uk.ac.gcu.myweatherapp.DataManager;
import uk.ac.gcu.myweatherapp.Models.Location;

public class PageViewModel extends ViewModel {

    public Location loc;
    public int locInd;
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
//            System.out.println("min temp: "+loc.getDays().get(input).getDescription().get("Minimum Temperature"));
//            System.out.println("min temp: "+loc.getDays().get(input).getDescription().keySet());
            return loc.getDays().get(input).getDescription().get("Minimum Temperature");
        }
    });

    private LiveData<String> mHumidity = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
//            System.out.println("Humidity check input "+loc.getDays().get(input).toString());
            return loc.getDays().get(input).getDescription().get("Humidity");
//            return loc.getDays().get(input).;
        }
    });

    private LiveData<String> mWind = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return loc.getDays().get(input).getDescription().get("Wind Speed");
        }
    });

    private LiveData<String> mHot = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            if (loc.getDays().get(input).getDay()!= "tonight")
                return loc.getDays().get(input).getDescription().get("Maximum Temperature");
            return "";
        }
    });
    private LiveData<String> mBrief = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
//            System.out.println("brief check input "+loc.getDays().get(input).getBrief());
//            return loc.getDays().get(input).getDescription();
            return loc.getDays().get(input).getBrief();
        }
    });
    private LiveData<String> weatherIcon = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
//            System.out.println("weather check input "+loc.getDays().get(input).getBrief());
//            return loc.getDays().get(input).getDescription();
            String d="";
            if(loc.getDays().get(input).getDay().equalsIgnoreCase("tonight"))
                d=" night";
            return loc.getDays().get(input).getBrief().replace(" ","").toLowerCase()+d;
        }
    });

    private LiveData<Map<String,String>> attributes = Transformations.map(mIndex, new Function<Integer, Map<String,String>>() {
        @Override
        public Map<String,String> apply(Integer input) {
            return loc.getDays().get(input).getDescription();
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
        locInd = index;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setLocation(int i){
        loc = DataManager.getInstance().locations.get(i);
//        System.out.println("from set location paview model "+loc.getCountryCode());
    }

    public String getPubDate(){return loc.getPubDate();}


    public LiveData<String> getHumidity(){
        return mHumidity;
    }
    public LiveData<String> getWind(){
        return mWind;
    }
    public LiveData<String> getHot(){
        return mHot;
    }

    public LiveData<String> getWeatherIcon(){
        return weatherIcon;
    }

    public LiveData<String> getBrief(){ return mBrief;}

    public LiveData<Map<String,String>> getAttributes(){
        return attributes;
    }
}