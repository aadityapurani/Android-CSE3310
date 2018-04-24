package com.team7.cse.mavevent;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class AddResourcesActivity extends AppCompatActivity implements OnItemSelectedListener {

    int rowSelected=-1;
    List<Integer> events_id;
    List<String> events_friendly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resources);
        final EditText item_field   = (EditText)findViewById(R.id.add_resources_item_id);
        final EditText quantity_field   = (EditText)findViewById(R.id.add_resources_quantity_id);
        final EditText cost_field   = (EditText)findViewById(R.id.add_resources_quantity_id);


        ////////////////////////////////////////////////////////////////////////////////////////////////////SPINNER LOGIC
        Spinner add_resources_event_spinner = (Spinner) findViewById(R.id.add_resources_event_spinner_id);
        add_resources_event_spinner.setOnItemSelectedListener(this);

        events_friendly = new ArrayList<String>();//this is the user-viewable name
        events_friendly.add("Select Event");
        events_friendly.add("Event A");
        events_friendly.add("Event B");
        events_friendly.add("Event C");

        events_id = new ArrayList<Integer>();//this is the corresponding dB ID
        events_id.add(0);
        events_id.add(1);
        events_id.add(2);
        events_id.add(3);

        //in reality, we need a control structure that will query the DB and for each
        // matching query add it to the events_friendly, events_id lists (while move to next)

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, events_friendly);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        add_resources_event_spinner.setAdapter(dataAdapter);
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        // Submit Button
        Button add_resources_submit_button = (Button) findViewById(R.id.add_resources_submit_id);
        add_resources_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowSelected>0)
                {
                    String item = item_field.getText().toString();
                    int quantity = Integer.parseInt(quantity_field.getText().toString());
                    double cost = Double.parseDouble(cost_field.getText().toString());
                    int event_id = events_id.get(rowSelected);
                    //logic here to submit the add resources request
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "You didn't select an event!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

        }

        //@Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         rowSelected = position;

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
        }
}