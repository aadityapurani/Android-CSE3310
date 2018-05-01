package com.team7.cse.mavevent;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.team7.cse.mavevent.DatabaseHelper;

/**
 * Created by aadit on 4/27/2018.
 */

public class ViewEventCalendarActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper handler;
    ArrayList<Event> pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_calendar);


        //date base
        final DatabaseHelper db = new DatabaseHelper(ViewEventCalendarActivity.this);


        // SharedPreferences called just because I want to use the User-ID
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
        int type = db.getUserType(user.getId());
        if(type == 0)
            pb = db.getAcceptedCatererEvents(user.getId());
        else {
            pb = db.getUserEvents(user.getId());
            //pb = db.getAllEvents();
        }
        //caterer ID
        final int cID=user.getId();

        listView = (ListView) findViewById(R.id.vec_calendar_id);
        // get data
        //pb=db.getAllEvents();
        //populate String array
        String[] testArray1 =new String[pb.size()];
        int i=0;
        for(Event p : pb ){
            testArray1[i]=p.getName() + "   " + p.getDate();
            i++;
        }

        //convert to list
        List<String> testList = Arrays.asList(testArray1);

        // Instanciating Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.custom_list_view, testList);

        // setting adapter on listview
        listView.setAdapter(adapter);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewEventCalendarActivity.this);


                builder.setTitle("Attention");


                builder.setMessage("You can approve an event by pressing Accept otherwise click on Decline");


                //Yes Button
                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Event p=pb.get(position);
                        db.acceptPendingEvents(p.getId(), cID);

                        Toast.makeText(getApplicationContext(),"Event Approved",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    }
                } );

                //No Button
                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Event p=pb.get(position);
                        db.rejectPendingEvents(p.getId());

                        Toast.makeText(getApplicationContext(),"Event Declined",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });


                //Cancel Button
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                // Toast.makeText(CatererRequest.this, testArray1[position], Toast.LENGTH_SHORT).show();
            }
        });
        */


    }


}
