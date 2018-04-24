package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CatererActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer);

        // Caterer Homepage - > Add Resources Page
        final Button caterer_add_resources_Button = (Button) findViewById(R.id.caterer_add_resources_id);
        caterer_add_resources_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_resource_Intent = new Intent(CatererActivity.this, AddResourcesActivity.class);
                CatererActivity.this.startActivity(add_resource_Intent);
            }
        });

        // Caterer Homepage - > Create Event Plan Page
        final Button create_event_plan_Button = (Button) findViewById(R.id.caterer_create_event_plan_id);
        create_event_plan_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create_event_plan_Intent = new Intent(CatererActivity.this, CreateEventPlan.class);
                CatererActivity.this.startActivity(create_event_plan_Intent);
            }
        });

        // Caterer Homepage - > Assign Staff Page
        final Button assign_staff_Button = (Button) findViewById(R.id.caterer_assign_staff_id);
        assign_staff_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assign_staff_Intent = new Intent(CatererActivity.this, AssignStaffActivity.class);
                CatererActivity.this.startActivity(assign_staff_Intent);
            }
        });

        // Caterer Homepage - > Schedule Venue/Location Page
        final Button schedule_venue_Button = (Button) findViewById(R.id.caterer_schedule_location_id);
        schedule_venue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schedule_venue_Intent = new Intent(CatererActivity.this, ScheduleVenueActivity.class);
                CatererActivity.this.startActivity(schedule_venue_Intent);
            }
        });

        // Caterer Homepage - > View Event Details Page
        final Button view_event_details_Button = (Button) findViewById(R.id.caterer_view_event_details_id);
        view_event_details_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_event_details_Intent = new Intent(CatererActivity.this, ViewEventDetailsActivity.class);
                CatererActivity.this.startActivity(view_event_details_Intent);
            }
        });

        // Caterer Homepage - > Delete Event Page
        final Button delete_event_Button = (Button) findViewById(R.id.caterer_delete_event_id);
        delete_event_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delete_event_Intent = new Intent(CatererActivity.this, DeleteEventActivity.class);
                CatererActivity.this.startActivity(delete_event_Intent);
            }
        });

    }
}