package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        // Just Testing the stuff out
        final Button view_events_Button = (Button) findViewById(R.id.view_events_id);
        view_events_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_events_Intent = new Intent(StaffActivity.this, ViewEventsActivity.class);
                StaffActivity.this.startActivity(view_events_Intent);
            }
        });

    }
}
