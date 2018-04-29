package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aadit on 4/28/2018.
 */

public class CatererViewApproved extends AppCompatActivity {

    ListView listView;
    DatabaseHelper handler;
    ArrayList<PendingEventBean> pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_approved_events);


        final DatabaseHelper db = new DatabaseHelper(CatererViewApproved.this);
        listView = (ListView) findViewById(R.id.approvedList);
        pb=db.getApprovedEvents();
        String[] testArray1 =new String[pb.size()];
        int i=0;
        for(PendingEventBean p : pb ){
            testArray1[i]=p.getEventName();
            i++;
        }
        List<String> testList = Arrays.asList(testArray1);
        // Instanciating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, testList);

        // setting adapter on listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                Intent view_approved_Intent = new Intent(CatererViewApproved.this, CatererActivity.class);
                CatererViewApproved.this.startActivity(view_approved_Intent);
            }
        });
    }
}
