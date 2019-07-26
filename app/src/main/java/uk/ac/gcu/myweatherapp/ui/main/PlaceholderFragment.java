package uk.ac.gcu.myweatherapp.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import uk.ac.gcu.myweatherapp.ActivityLocation;
import uk.ac.gcu.myweatherapp.DownloadIcon;
import uk.ac.gcu.myweatherapp.R;
import uk.ac.gcu.myweatherapp.utils.CommonGestureListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_LOCATION_INDEX = "section_number";
    private static int i;
    private PageViewModel pageViewModel;
    private GestureDetectorCompat imageViewGestureDetectorCompat = null;

    public static PlaceholderFragment newInstance(int index,int locationIndex) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        i = locationIndex;
//        bundle.putInt(ARG_LOCATION_INDEX, locationIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
//        int locIndex = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
//            locIndex = getArguments().getInt(ARG_LOCATION_INDEX);
        }
        pageViewModel.setLocation(i);
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView brief = root.findViewById(R.id.brief);
        ImageView icon = root.findViewById(R.id.iconImg);
        final TextView hotView = root.findViewById(R.id.maxTemp);
        final TextView windView = root.findViewById(R.id.wind);
        final TextView humidityView = root.findViewById(R.id.humidity);
        final TextView temperatureTextView = root.findViewById(R.id.temperature);
        final RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.attributesRecyclerView);
        final String[] br = {""};
        final Context context=getContext();
        ImageView moreIcon = (ImageView) root.findViewById(R.id.moreIcon);
        LinearLayout ll = (LinearLayout) root.findViewById(R.id.linearLayout2);

        if(this.imageViewGestureDetectorCompat==null)
        {
            // Create custom gesture listener.
            CommonGestureListener imageGestureListener = new CommonGestureListener();

            // Set source view object.
            imageGestureListener.setSrcView(moreIcon);
//            imageGestureListener.setActivity(this);

            // Create activity gesture detector object use activity gesture listener.
            imageViewGestureDetectorCompat = new GestureDetectorCompat(context, imageGestureListener);

            // Set double tap listener.
            imageViewGestureDetectorCompat.setOnDoubleTapListener(imageGestureListener);
        }

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll.getLayoutParams();
//        moreIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("more clicked");
////                ll.setLayoutParams(new LinearLayout.LayoutParams(ll.getLayoutParams().width, LinearLayout.LayoutParams.WRAP_CONTENT));
//            }
//        });
//        ll.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                float dX=0;
//                float dY=0;
//                int lastAction=0;
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        dX = v.getX() - event.getX();
//                        dY = v.getY() - event.getY();
//                        lastAction = MotionEvent.ACTION_DOWN;
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        v.setY(event.getY() + dY);
//                        v.setX(event.getX() + dX);
//                        lastAction = MotionEvent.ACTION_MOVE;
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        if (lastAction == MotionEvent.ACTION_DOWN)
//                            System.out.println("ACTION DOWN");
////                            Toast.makeText(PlaceholderFragment.this, "Clicked!", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    default:
//                        return false;
//                }
//                return true;
//            }
//
//        });
        ll.setOnTouchListener(new View.OnTouchListener() {
            float dX;
            float dY;
            int lastAction;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("image view gestures: +++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(event.getAction());
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
//                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setY(event.getRawY() + dY);
//                        Float newHeight = params.height+dY;
//                        ll.setLayoutParams(new LinearLayout.LayoutParams(ll.getLayoutParams().width, ll.getHeight()));
//                        ll.setLayoutParams(new LinearLayout.LayoutParams(params.width,newHeight));
//                        v.setX(event.getRawX() + dX);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (lastAction == MotionEvent.ACTION_DOWN)
                            System.out.println("ACTION DOWN");
//                            Toast.makeText(PlaceholderFragment.this, "Clicked!", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return false;
                }
                return true;
                }
        });















//        TODO: update this when working on pull up interaction
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
        final RecyclerView.Adapter[] mAdapter = new RecyclerView.Adapter[1];

        pageViewModel.getAttributes().observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(@Nullable Map<String, String> map) {
                mAdapter[0] = new AttributesAdapter(map);
                recyclerView.setAdapter(mAdapter[0]);

            }
        });


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                temperatureTextView.setText(s);
            }
        });

        pageViewModel.getWind().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("On changed "+s);
                windView.setText(s);
            }
        });
        pageViewModel.getHot().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                hotView.setText(s);
            }
        });
        pageViewModel.getHumidity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                humidityView.setText(s);
            }
        });
        pageViewModel.getBrief().observe(this, new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {
                brief.setText(s);
            }
        });
        pageViewModel.getWeatherIcon().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                icon.setImageDrawable(getImage(context,s));
            }
        });

        return root;
    }

    public static Drawable getImage(Context c, String ImageName) {
        System.out.println("drawable "+ ImageName);

        int name = c.getResources().getIdentifier(ImageName.toLowerCase().replaceAll("\\s",""),
                "drawable",c.getPackageName());
        if (name == 0){
            name = c.getResources().getIdentifier("na","drawable",c.getPackageName());
        }
        return ContextCompat.getDrawable(c,name );
    }



}