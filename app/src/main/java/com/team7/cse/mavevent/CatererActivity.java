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

        // Just Testing the stuff out
        final Button caterer_add_resources_Button = (Button) findViewById(R.id.caterer_add_resources_id);
        caterer_add_resources_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_resource_Intent = new Intent(CatererActivity.this, AddResourcesActivity.class);
                CatererActivity.this.startActivity(add_resource_Intent);
            }
        });

        // Just Testing the stuff out
        final Button create_event_plan_Button = (Button) findViewById(R.id.button26);
        create_event_plan_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create_event_plan_Intent = new Intent(CatererActivity.this, CreateEventPlan.class);
                CatererActivity.this.startActivity(create_event_plan_Intent);
            }
        });

    }
}