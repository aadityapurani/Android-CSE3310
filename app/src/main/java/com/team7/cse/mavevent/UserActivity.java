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
        final Button view_events_Button = (Button) findViewById(R.id.user_request_events_id);
        view_events_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request_event_Intent = new Intent(UserActivity.this, CreateEventPlan.class);
                UserActivity.this.startActivity(request_event_Intent);
            }
        });

        // User Homepage - > View Reserved Event Page
        final Button view_reserved_event_Button = (Button) findViewById(R.id.user_view_reserved_event_id);
        view_reserved_event_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent view_reserved_event_Intent = new Intent(UserActivity.this, ViewReservedEventsActivity.class);
                Intent view_reserved_event_Intent = new Intent(UserActivity.this, UserViewReservedEvents.class);
                UserActivity.this.startActivity(view_reserved_event_Intent);
            }
        });

        // User Homepage - > Cancel Events Page
        final Button cancel_events_Button = (Button) findViewById(R.id.user_cancel_event_id);
        cancel_events_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel_events_Intent = new Intent(UserActivity.this, CancelEventActivity.class);
                UserActivity.this.startActivity(cancel_events_Intent);
            }
        });
        // User Homepage - > View Schedule Page
        final Button view_event_schedule_Button = (Button) findViewById(R.id.user_view_schedule_id);
        view_event_schedule_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_event_schedule_Intent = new Intent(UserActivity.this, ViewEventCalendarActivity.class);
                UserActivity.this.startActivity(view_event_schedule_Intent);
            }
        });

        // User Homepage - > Main Page
        final Button logout_Button = (Button) findViewById(R.id.user_logout_id);
        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout_Intent = new Intent(UserActivity.this, MainActivity.class);
                UserActivity.this.startActivity(logout_Intent);
            }
        });

    }

}