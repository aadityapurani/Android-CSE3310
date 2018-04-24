package com.team7.cse.mavevent;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class CreateEventPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_plan);

        final EditText attendees_field   = (EditText)findViewById(R.id.cep_attendees_id);

        Switch alcohol_switch = (Switch) findViewById(R.id.cep_alcohol_id);
        Switch formality_switch = (Switch) findViewById(R.id.cep_formality_id);





        // Submit Button
        Button cep_submit_button = (Button) findViewById(R.id.cep_submit_id);
        cep_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

}
