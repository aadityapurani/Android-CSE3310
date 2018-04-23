package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // User Homepage - > Request Event Page
        final Button view_events_Button = (Button) findViewById(R.id.request_event_id);
        view_events_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request_event_Intent = new Intent(UserActivity.this, RequestEventActivity.class);
                UserActivity.this.startActivity(request_event_Intent);
            }
        });

        /*// User Homepage - > View Reserved Event Page
        final Button view_reserved_event_Button = (Button) findViewById(R.id.request_event_id);
        view_events_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request_event_Intent = new Intent(UserActivity.this, RequestEventActivity.class);
                UserActivity.this.startActivity(request_event_Intent);
            }
        });*/

    }
}