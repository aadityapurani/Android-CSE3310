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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class AddResourcesActivity extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resources);

        // Just Testing the stuff out
        Button add_resources_submit_button = (Button) findViewById(R.id.add_resources_submit_id);
        add_resources_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic here to submit the add resources request
            }
        });


        Spinner add_resources_event_spinner = (Spinner) findViewById(R.id.add_resources_event_spinner_id);
        add_resources_event_spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Event A");
        categories.add("Event B");
        categories.add("Event C");
        categories.add("Event D");
        categories.add("Event E");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        add_resources_event_spinner.setAdapter(dataAdapter);

        }

        //@Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String item = parent.getItemAtPosition(position).toString();
         Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
        }
}