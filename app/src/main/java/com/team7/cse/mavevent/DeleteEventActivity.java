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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatabaseHelper myDb;
    EditText editTextId;
    List<Integer> events_id;
    List<String> events_friendly;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);
        myDb = new DatabaseHelper(this);

        EventList();

        DeleteData();
    }
        ///////////////////////////////////////////////////////////////////////////Spinner for event
        public void EventList() {
            Spinner event_list_spinner = (Spinner) findViewById(R.id.event_list_spinner_id);
            event_list_spinner.setOnItemSelectedListener(this);


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

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, events_friendly);

            dataAdapter.setDropDownViewResource(R.layout.spinner_item);

            event_list_spinner.setAdapter(dataAdapter);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////delete Button
        public void DeleteData() {
            btnDelete = (Button) findViewById(R.id.button_delete);

            btnDelete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
//                            if (deletedRows > 0)
                                Toast.makeText(DeleteEventActivity.this, "Event Deleted", Toast.LENGTH_LONG).show();
//                            else
                                Toast.makeText(DeleteEventActivity.this, "Event not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}