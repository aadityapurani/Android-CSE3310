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

        // setting adapter on listview
        final Button view_calender_Button = (Button) findViewById(R.id.vec_exit_id);
        view_calender_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*
        listView.setAdapter(adapter);
        Button cep_submit_button = (Button) findViewById(R.id.vec_exit_id);
        cep_submit_button.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     finish();
                                                 }
                                             });
        */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        Event p = pb.get(position);
                        //db.acceptPendingEvents(p.getId(), );

                        Toast.makeText(getApplicationContext(), "Event Approved", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    }
                });

                //No Button
                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Event p = pb.get(position);
                        db.rejectPendingEvents(p.getId());

                        Toast.makeText(getApplicationContext(), "Event Declined", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });


                //Cancel Button
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancel button Clicked", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                // Toast.makeText(CatererRequest.this, testArray1[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}