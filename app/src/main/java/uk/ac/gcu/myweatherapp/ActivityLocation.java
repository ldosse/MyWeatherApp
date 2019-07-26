package uk.ac.gcu.myweatherapp;

import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.ac.gcu.myweatherapp.Models.Location;
import uk.ac.gcu.myweatherapp.ui.main.SectionsPagerAdapter;
import uk.ac.gcu.myweatherapp.utils.CommonGestureListener;

public class ActivityLocation extends AppCompatActivity {
    Location location;
    Context context;
    LinearLayout ll;
    private static final String DEBUG_TAG = "Gestures";
    // This gesture detector is used in activity's onTouchEvent method.
    private GestureDetectorCompat activityGestureDetectorCompat = null;
    // This gesture detector is used in image view's onTouchListener's onTouch method.
    private GestureDetectorCompat imageViewGestureDetectorCompat = null;
    // This gesture detector is used in linearLayout view's onTouchListener's onTouch method.
    private GestureDetectorCompat linearGestureDetectorCompat = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra("index",0);
        context =ActivityLocation.this;
        location = DataManager.getInstance().locations.get(position);
        System.out.println("You clicked index num "+ position);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),position);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TextView loc = (TextView) findViewById(R.id.location_title);
        loc.setText(DataManager.getInstance().locations.get(position).getName());


        FloatingActionButton nextFAB = findViewById(R.id.nextFAB);

        nextFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityLocation.class);
                intent.putExtra("index", DataManager.getInstance().getNext(position));
                context.startActivity(intent);
            }
        });
        nextFAB.setAlpha(0.2f);

        FloatingActionButton previousFAB = findViewById(R.id.previousFAB);

        previousFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityLocation.class);
                intent.putExtra("index", DataManager.getInstance().getPrevious(position));
                context.startActivity(intent);
            }
        });
        previousFAB.setAlpha(0.1f);

//        LinearLayout linearLayout = findViewById(R.id.view_pager);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();

//        ImageView more = (ImageView)findViewById(R.id.moreIcon);

//        more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("more clicked");
//                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(params.width, LinearLayout.LayoutParams.WRAP_CONTENT));
//            }
//        });


        if(activityGestureDetectorCompat==null)
        {
            // Create custom gesture listener.
            CommonGestureListener activityGestureListener = new CommonGestureListener();

            // Set source view object.
            activityGestureListener.setSrcView(viewPager);
            activityGestureListener.setActivity(this);

            // Create activity gesture detector object use activity gesture listener.
            activityGestureDetectorCompat = new GestureDetectorCompat(this, activityGestureListener);

            // Set double tap listener.
            activityGestureDetectorCompat.setOnDoubleTapListener(activityGestureListener);
        }


        // Set a new OnTouchListener to image view.
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // When image view ontouch event occurred, call it's gesture detector's onTouchEvent method.
                activityGestureDetectorCompat.onTouchEvent(motionEvent);
                System.out.println("------------------------------------------------------------");
                // Return true to tell android OS this listener has consumed the event, do not need to pass the event to other listeners.
                return true;
            }
        });


//        get position of the card clicked in the landing activity




//        if (moreIcon!=null)
//        moreIcon.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                // When image view ontouch event occurred, call it's gesture detector's onTouchEvent method.
////                imageViewGestureDetectorCompat.onTouchEvent(motionEvent);
//                System.out.println("image view gestures: +++++++++++++++++++++++++++++++++++++++++++++++++++");
//                // Return true to tell android OS this listener has consumed the event, do not need to pass the event to other listeners.
//                return true;
//            }
//        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // When this activity's ouTouchEvent occurred. Call activity gesture detector onTouchEvent method.
        this.activityGestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

//    onActivity

//    protected void setUp() {

}