package com.team7.cse.mavevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ScheduleHallActivity extends AppCompatActivity {

    ListView listView;
    Event e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_hall);

        e = (Event)getIntent().getSerializableExtra("EVENT");
        final String eventNameOfcourse = e.getName();

        // Database Helper as needed
        final DatabaseHelper db = new DatabaseHelper(ScheduleHallActivity.this);

        listView = (ListView) findViewById(R.id.venueList);
        final String[] testArray = getResources().getStringArray(R.array.hallnames);
        List<String> testList = Arrays.asList(testArray);
        // Instantiating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.custom_list_view, testList);

        // setting adapter on listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                db.allocateHall(position+1, eventNameOfcourse);

                Toast.makeText(ScheduleHallActivity.this, testArray[position], Toast.LENGTH_SHORT).show();
                //add code here to update hall given event
                finish();
            }
        });


    }
}