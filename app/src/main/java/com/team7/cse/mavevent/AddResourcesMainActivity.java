package com.team7.cse.mavevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Casey on 4/26/18.
 */

public class AddResourcesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_resources_main);

        // Resources Main -> Choose Venue
        final Button choose_venue_Button = (Button)findViewById(R.id.arm_choose_venue_id);
        choose_venue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose_venue_Intent = new Intent(AddResourcesMainActivity.this, ChooseVenueActivity.class);
                AddResourcesMainActivity.this.startActivity(choose_venue_Intent);
            }
        });

        // Resources Main - > Add Drink
        final Button caterer_view_requests_Button = (Button) findViewById(R.id.arm_add_drink_id);
        caterer_view_requests_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent add_drink_Intent = new Intent(AddResourcesMainActivity.this, AddDrinkActivity.class);
                AddResourcesMainActivity.this.startActivity(add_drink_Intent);

            }
        });

        // Resources Main - > Add Entertainment Items
        final Button add_entertainment_items_Button = (Button) findViewById(R.id.arm_aer_id);
        add_entertainment_items_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_et_Intent = new Intent(AddResourcesMainActivity.this, AddEntertainmentItemsActivity.class);
                AddResourcesMainActivity.this.startActivity(add_et_Intent);
            }
        });

    }
}
