package com.team7.cse.mavevent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CatererActivity extends AppCompatActivity {

    Event e;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer);

        e = (Event)getIntent().getSerializableExtra("EVENT");
        t=(TextView)findViewById(R.id.textViewEventName);
        t.setText(e.getName());
        // Caterer Homepage - > Add Resources Page
        final Button caterer_add_resources_Button = (Button) findViewById(R.id.caterer_add_resources_id);
        caterer_add_resources_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_resource_Intent = new Intent(CatererActivity.this, AddResourcesMainActivity.class);
                CatererActivity.this.startActivity(add_resource_Intent);
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
        final Button schedule_venue_Button = (Button) findViewById(R.id.caterer_schedule_venue_id);
        schedule_venue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schedule_hall_Intent = new Intent(CatererActivity.this, ScheduleHallActivity.class);
                CatererActivity.this.startActivity(schedule_hall_Intent);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(CatererActivity.this);


                builder.setTitle("Attention");


                builder.setMessage("Do you want to delete this event or drop it? ");


                //Yes Button
                builder.setPositiveButton("Drop", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //code to delete the event from the DB goes here

                        Toast.makeText(getApplicationContext(),"Event Deleted",Toast.LENGTH_LONG).show();
                        finish();

                    }
                });

                //No Button
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //code to move event back to unapproved column goes here

                        Toast.makeText(getApplicationContext(),"Event Dropped",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        finish();
                    }
                });


                //Cancel Button
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        // Caterer Homepage - > Main Page
        final Button logout_Button = (Button) findViewById(R.id.caterer_logout_id);
        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout_Intent = new Intent(CatererActivity.this, MainActivity.class);
                CatererActivity.this.startActivity(logout_Intent);
            }
        });

    }
}