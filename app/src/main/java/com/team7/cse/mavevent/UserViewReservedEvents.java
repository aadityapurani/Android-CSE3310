package com.team7.cse.mavevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aadit on 4/29/2018.
 */

public class UserViewReservedEvents extends AppCompatActivity {

    DatabaseHelper handler;
    ListView listView;
    ArrayList<PendingEventBean> pb;
    Event e = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_reserved_events);



        // Database Helper
        final DatabaseHelper db = new DatabaseHelper(UserViewReservedEvents.this);

        // Getting the ListView
        listView = (ListView) findViewById(R.id.ListForUser);

        // SharedPreferences called just because I want to use the User-ID
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
        int userID=user.getId();

        // Making request to the Database
        pb=db.getuEvents(userID);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                // Need to pass Event Object to the Intent Itself.
                Intent view_approved_Intent = new Intent(UserViewReservedEvents.this, ViewReservedEventsActivity.class);
                e.setName(testArray1[position]);
                view_approved_Intent.putExtra("EVENT", e);
                UserViewReservedEvents.this.startActivity(view_approved_Intent);
            }
        });


    }
}