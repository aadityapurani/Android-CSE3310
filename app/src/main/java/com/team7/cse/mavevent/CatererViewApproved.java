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
    Event e = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_approved_events);


        final DatabaseHelper db = new DatabaseHelper(CatererViewApproved.this);
        listView = (ListView) findViewById(R.id.approvedList);
        pb=db.getApprovedEvents();
        final String[] testArray1 =new String[pb.size()];
        final Integer[] testArray2 =new Integer[pb.size()];
        int i=0;
        for(PendingEventBean p : pb ){
            testArray1[i]= p.getEventName();
            testArray2[i] = p.getId();
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
                Intent view_approved_Intent = new Intent(CatererViewApproved.this, CatererActivity.class);
                e.setName(testArray1[position]);
                e.setId(testArray2[position]);
                view_approved_Intent.putExtra("EVENT", e);
                CatererViewApproved.this.startActivity(view_approved_Intent);
            }
        });
    }
}
