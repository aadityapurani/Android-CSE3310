package com.team7.cse.mavevent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CreateEventPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_plan);
<<<<<<< HEAD

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
=======
>>>>>>> parent of 096a8bc... Merge branch 'master' of https://github.com/aadityapurani/Android-CSE3310
    }

}
