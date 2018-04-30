package com.team7.cse.mavevent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import static java.lang.Integer.parseInt;

public class ViewReservedEventsActivity extends AppCompatActivity {

    DatabaseHelper handler;
    Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reserved_events);

        // Let's get serializable data
        e = (Event)getIntent().getSerializableExtra("EVENT");
        String gettname = e.getName();

        // Database Helper
        final DatabaseHelper db = new DatabaseHelper(ViewReservedEventsActivity.this);

        // SharedPreferences called just because I want to use the User-ID
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
        int userID=user.getId();
        String[] updatedView = db.viewReservedEvents(userID, gettname);
        updateTextView(updatedView);


    }

    public void updateTextView(String[] updatedView) {


        TextView textViewa = (TextView) findViewById(R.id.updateName);
        TextView textViewb = (TextView) findViewById(R.id.updateStartTime);
        TextView textViewc = (TextView) findViewById(R.id.updateEndTime);
        TextView textViewd = (TextView) findViewById(R.id.updatedAccepted);

        if (updatedView[3] != "N/A"){
            int somenum = Integer.parseInt(updatedView[3]);
            if(somenum == 1){
                updatedView[3] = "Yes";
            }else if(somenum == 0){
                updatedView[3] = "Pending";
            }else if(somenum == 2){
                updatedView[3] = "No";
            }else if(somenum == 3){
                updatedView[3] = "Deleted";
            }

        }

        textViewa.setText(updatedView[0]);
        textViewb.setText(updatedView[1]);
        textViewc.setText(updatedView[2]);
        textViewd.setText(updatedView[3]);

    }

}
