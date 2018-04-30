package com.team7.cse.mavevent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewEventCalendarActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper handler;
    ArrayList<Event> pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_calendar);

        final DatabaseHelper db = new DatabaseHelper(ViewEventCalendarActivity.this);

        listView = (ListView) findViewById(R.id.vec_calendar_id);
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User","");
        UserBaseModel userModel = gson.fromJson(json,UserBaseModel.class);
        ArrayList<Event> events = null;
        boolean isUser = true;
        //Upload the correct data
        if(userModel.type==0){
            events = db.getAcceptedCatererEvents(userModel.getId());
            isUser = false;
        }
        else{
            //events = db.getUserEvents(userModel.getId());
            events = new ArrayList<Event>();
        }
        String[] testArray1 = new String[pb.size()];
        int i = 0;
        for (Event p : pb) {
            testArray1[i] = p.getName() + p.getDate();
            i++;
        }
        List<String> testList = Arrays.asList(testArray1);

        // Instanciating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.custom_list_view, testList);


    }
}