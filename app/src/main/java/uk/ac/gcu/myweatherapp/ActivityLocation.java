package uk.ac.gcu.myweatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import uk.ac.gcu.myweatherapp.ui.main.SectionsPagerAdapter;

public class ActivityLocation extends AppCompatActivity {
    Location location;
    Context context;
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
        loc.setText(DataManager.getInstance().locations.get(position).name);

        FloatingActionButton nextFAB = findViewById(R.id.nextFAB);

        nextFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityLocation.class);
                intent.putExtra("index", DataManager.getInstance().getNext(position));
                context.startActivity(intent);
            }
        });

        FloatingActionButton previousFAB = findViewById(R.id.previousFAB);

        previousFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityLocation.class);
                intent.putExtra("index", DataManager.getInstance().getPrevious(position));
                context.startActivity(intent);
            }
        });

//        get position of the card clicked in the landing activity

    }
}