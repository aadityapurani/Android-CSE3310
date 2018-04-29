package com.team7.cse.mavevent;

import android.content.Intent;
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
        setContentView(R.layout.activity_pending_request);

        final DatabaseHelper db = new DatabaseHelper(ViewEventCalendarActivity.this);

        listView = (ListView) findViewById(R.id.pendingList);
        pb=db.getAllEvents();
        String[] testArray1 =new String[pb.size()];
        int i=0;
        for(Event p : pb ){
            testArray1[i]=p.getName();
            i++;
        }
        List<String> testList = Arrays.asList(testArray1);
        // Instanciating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.custom_list_view, testList);

        // setting adapter on listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
    }
}