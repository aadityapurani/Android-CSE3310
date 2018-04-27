package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class AssignStaffActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int rowSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_staff);

        ////////////////////////////////////////////////////////////////////////////////////////////////////SPINNER LOGIC
        Spinner assign_staff_event_spinner = (Spinner) findViewById(R.id.assign_staff_event_id);
        assign_staff_event_spinner.setOnItemSelectedListener(this);

        ArrayList<String> events_friendly = new ArrayList<String>();//this is the user-viewable name
        events_friendly.add("Select Event");
        events_friendly.add("Event A");
        events_friendly.add("Event B");
        events_friendly.add("Event C");

        ArrayList<Integer> events_id = new ArrayList<Integer>();//this is the corresponding dB ID
        events_id.add(0);
        events_id.add(1);
        events_id.add(2);
        events_id.add(3);

        //in reality, we need a control structure that will query the DB and for each
        // matching query add it to the events_friendly, events_id lists (while move to next)

        ArrayAdapter<String> dataEventAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, events_friendly);

        dataEventAdapter.setDropDownViewResource(R.layout.spinner_item);

        assign_staff_event_spinner.setAdapter(dataEventAdapter);
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////SPINNER LOGIC
        Spinner assign_staff_staff_spinner = (Spinner) findViewById(R.id.assign_staff_staff_id);
        assign_staff_staff_spinner.setOnItemSelectedListener(this);

        ArrayList<String> staff_friendly = new ArrayList<String>();//this is the user-viewable name
        staff_friendly.add("Select Staff");
        staff_friendly.add("Jon");
        staff_friendly.add("Aaditya");
        staff_friendly.add("David");

        ArrayList<Integer> staff_id = new ArrayList<Integer>();//this is the corresponding dB ID
        staff_id.add(0);
        staff_id.add(1);
        staff_id.add(2);
        staff_id.add(3);

        //in reality, we need a control structure that will query the DB and for each
        // matching query add it to the events_friendly, events_id lists (while move to next)

        ArrayAdapter<String> dataStaffAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, staff_friendly);

        dataStaffAdapter.setDropDownViewResource(R.layout.spinner_item);

        assign_staff_staff_spinner.setAdapter(dataStaffAdapter);
        ////////////////////////////////////////////////////////////////////////////////////////////////////

    }
    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rowSelected = position;

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }
}
