package com.team7.cse.mavevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Casey on 4/26/18.
 */

public class CatererHomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_home_screen);

        // Caterer Homepage -> View Pending
        final Button caterer_view_pending_id_Button = (Button)findViewById(R.id.caterer_view_pending_id);
        caterer_view_pending_id_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_pending_Intent = new Intent(CatererHomeScreenActivity.this, CatererRequest.class);
                CatererHomeScreenActivity.this.startActivity(view_pending_Intent);
            }
        });

        // Caterer Homepage - > View Approved
        final Button caterer_view_requests_Button = (Button) findViewById(R.id.caterer_view_approved_id);
        caterer_view_requests_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent view_pending_Intent = new Intent(CatererHomeScreenActivity.this, CatererViewApproved.class);
                CatererHomeScreenActivity.this.startActivity(view_pending_Intent);

                //Intent view_approved_Intent = new Intent(CatererHomeScreenActivity.this, CatererActivity.class);
                //CatererHomeScreenActivity.this.startActivity(view_approved_Intent);
            }
        });
        // Caterer Homepage - > View Event Calender
        final Button view_calender_Button = (Button) findViewById(R.id.caterer_view_calendarsss_id);
        view_calender_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_calender_Intent = new Intent(CatererHomeScreenActivity.this, ViewEventCalendarActivity.class);
                CatererHomeScreenActivity.this.startActivity(view_calender_Intent);
            }
        });

        final Button logout_Button = (Button) findViewById(R.id.caterer_logout_id);
        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout_Intent = new Intent(CatererHomeScreenActivity.this, MainActivity.class);
                CatererHomeScreenActivity.this.startActivity(logout_Intent);
            }
        });

    }
}
