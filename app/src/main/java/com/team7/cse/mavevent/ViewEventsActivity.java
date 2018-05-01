package com.team7.cse.mavevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewEventsActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper handler;
    ArrayList<PendingEventBean> pb;
    Event e = new Event();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_view_event);

        final DatabaseHelper db = new DatabaseHelper(ViewEventsActivity.this);
        listView = (ListView) findViewById(R.id.staffEventList);


        // Shared Preferences
        // SharedPreferences called just because I want to use the User-ID
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
        int sID=user.getId();

        // JUNK
        pb=db.getStaffAssignedEvents(sID);
        final String[] testArray1 =new String[pb.size()];
        int i=0;
        for(PendingEventBean p : pb ){
            testArray1[i]=p.getEventName();
            i++;
        }

        List<String> testList = Arrays.asList(testArray1);
        // Instantiating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.custom_list_view, testList);

        // setting adapter on listview
        listView.setAdapter(adapter);



    }
}