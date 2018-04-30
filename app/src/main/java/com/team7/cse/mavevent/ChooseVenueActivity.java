package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import com.team7.cse.mavevent.Venue;

/**
 * Created by Casey on 4/29/18.
 */

public class ChooseVenueActivity extends AppCompatActivity {

    ListView listView;
    int selectedPos;
    ArrayList<String> venue;
    DatabaseHelper handler;
    ArrayList<PendingEventBean> pb;
    Event e = new Event();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_choose_venue);

        final DatabaseHelper db = new DatabaseHelper(ChooseVenueActivity.this);
        listView = (ListView) findViewById(R.id.venueList);

        venue = new ArrayList<String>();//this is the user-viewable name
        venue.add("Pizza");
        venue.add("French");
        venue.add("Chinese");
        venue.add("Mexican");
        venue.add("Italian");
        venue.add("American");
        venue.add("Greek");
        venue.add("Indian");
        venue.add("Japanese");

        e = (Event)getIntent().getSerializableExtra("EVENT");
        final Integer niceId= e.getId();



        // Instantiating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.custom_list_view, venue);

        // setting adapter on listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                // Need to pass Event Object to the Intent Itself.
               // selectedPos = position + 1;
                String food = venue.get(position);
                db.addNewVenue(food, niceId);

                finish();
            }
        });
    }
}
