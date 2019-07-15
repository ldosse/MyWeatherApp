package uk.ac.gcu.myweatherapp.ui.main;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import uk.ac.gcu.myweatherapp.DataManager;
import uk.ac.gcu.myweatherapp.Location;

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

    private LiveData<String> mDesc = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            System.out.println("mDesc check input "+loc.getDays().get(input).toString());
            return loc.getDays().get(input).toString();
//            return loc.getDays().get(input).;
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

    private LiveData<Map<String,String>> attributes = Transformations.map(mIndex, new Function<Integer, Map<String,String>>() {
        @Override
        public Map<String,String> apply(Integer input) {
            return loc.getDays().get(input).getDescription();
        }
    });
//            loc.getDays().get(mIndex).getDescription();

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

    public LiveData<String> getDesc(){
        return mDesc;
    }

    public LiveData<String> getBrief(){ return mBrief;}

    public LiveData<Map<String,String>> getAttributes(){
        return attributes;
    }
}