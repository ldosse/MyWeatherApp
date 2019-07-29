package uk.ac.gcu.myweatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.ac.gcu.myweatherapp.Models.Location;
import uk.ac.gcu.myweatherapp.ui.main.SectionsPagerAdapter;

public class ActivityLocation extends AppCompatActivity {
    public Location getLocation() {
        return location;
    }

    Location location;
    Context context;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra("index",0);
        context =ActivityLocation.this;
        location = DataManager.getInstance().locations.get(position);

        System.out.println("You clicked index num "+ position);

        this.setTitle(location.getName());
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),position);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

//        TextView loc = (TextView) findViewById(R.id.location_title);
//        loc.setText(DataManager.getInstance().locations.get(position).getName());


        FloatingActionButton nextFAB = findViewById(R.id.nextFAB);

        nextFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityLocation.class);
                intent.putExtra("index", DataManager.getInstance().getNext(position));
                context.startActivity(intent);
            }
        });
//        nextFAB.setAlpha(0.2f);

        FloatingActionButton previousFAB = findViewById(R.id.previousFAB);

        previousFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityLocation.class);
                intent.putExtra("index", DataManager.getInstance().getPrevious(position));
                context.startActivity(intent);
            }
        });
//        previousFAB.setAlpha(0.1f);


    }

}