package com.team7.cse.mavevent;

import android.content.DialogInterface;
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

import com.team7.cse.mavevent.DatabaseHelper;

/**
 * Created by aadit on 4/27/2018.
 */

public class CatererRequest extends AppCompatActivity {

    ListView listView;
    DatabaseHelper handler;
    ArrayList<PendingEventBean> pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request);

        final DatabaseHelper db = new DatabaseHelper(CatererRequest.this);

        listView = (ListView) findViewById(R.id.pendingList);
        pb=db.getPendingEvents();
        String[] testArray1 =new String[pb.size()];
        int i=0;
        for(PendingEventBean p : pb ){
            testArray1[i]=p.getEventName();
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
                // TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(CatererRequest.this);


                builder.setTitle("Attention");


                builder.setMessage("You can approve an event by pressing Accept otherwise click on Decline");


                //Yes Button
                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PendingEventBean p=pb.get(position);
                        db.acceptPendingEvents(p.getId());

                        Toast.makeText(getApplicationContext(),"Event Approved",Toast.LENGTH_LONG).show();
                        finish();
                        //startActivity(getIntent());
                    }
                });

                //No Button
                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        PendingEventBean p=pb.get(position);
                        db.rejectPendingEvents(p.getId());

                        Toast.makeText(getApplicationContext(),"Event Declined",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        //finish();
                        //startActivity(getIntent());
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


    }


}
