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
            System.out.println("min temp: "+loc.getDays().get(input).getDescription().get("Minimum Temperature"));
            System.out.println("min temp: "+loc.getDays().get(input).getDescription().keySet());
            return loc.getDays().get(input).getDescription().get("Minimum Temperature");
        }
    });

    private LiveData<String> mHumidity = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            System.out.println("Humidity check input "+loc.getDays().get(input).toString());
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
            return loc.getDays().get(input).getDescription().get("Maximum Temperature");
        }
    });
    private LiveData<String> mBrief = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            System.out.println("brief check input "+loc.getDays().get(input).getBrief());
//            return loc.getDays().get(input).getDescription();
            return loc.getDays().get(input).getBrief();
        }
    });
    private LiveData<String> weatherIcon = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            System.out.println("weather check input "+loc.getDays().get(input).getBrief());
//            return loc.getDays().get(input).getDescription();
            return loc.getDays().get(input).getBrief();
        }
    });

    private LiveData<Map<String,String>> attributes = Transformations.map(mIndex, new Function<Integer, Map<String,String>>() {
        @Override
        public Map<String,String> apply(Integer input) {
            return loc.getDays().get(input).getDescription();
        }
    });
//            loc.getDays().get(mIndex).getDescription();

//    private LiveData<Object> weatherIcon = Transformations.map(mIndex, new Function<Integer, Object>() {
//        @Override
//        public Bitmap apply(Integer input) {
//
//        }
//    });
//    private LiveData<Object> weatherIcon = Transformations.map(mIndex, new Function<Integer, Object>() {
//        @Override
//        public Bitmap apply(Integer input) {
//            String url = "static.bbci.co.uk/weather/0.3.203/images/icons/individual_57_icons/en_on_light_bg/";
//            Integer num = DataManager.getInstance().iconsMap.get(loc.getDays().get(input).getBrief().toLowerCase());
//            System.out.println("what is the number " + num);
//            try {
//                return new DownloadIcon().execute(url+num+".gif").get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    });

    public void setIndex(int index) {
        mIndex.setValue(index);
        locInd = index;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setLocation(int i){
        loc = DataManager.getInstance().locations.get(i);
        System.out.println("from set location paview model "+loc.getCountryCode());
    }



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