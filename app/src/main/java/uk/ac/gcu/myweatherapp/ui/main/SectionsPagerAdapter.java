package uk.ac.gcu.myweatherapp.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import uk.ac.gcu.myweatherapp.DataManager;
import uk.ac.gcu.myweatherapp.Location;
import uk.ac.gcu.myweatherapp.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

//    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_title_1, R.string.tab_title_2,R.string.tab_title_3};
    private final Context mContext;
    private Location location;
    private int locIndex;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int position) {
        super(fm);
        mContext = context;
        locIndex = position;
        location = DataManager.getInstance().locations.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position , locIndex);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[position]);
        String day = location.getDays().get(position).getDay();
        System.out.println(day);
        return day;
    }

    @Override
    public int getCount() {
        return location.getDays().size();
    }
}