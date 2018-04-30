package com.team7.cse.mavevent;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignStaffActivity extends AppCompatActivity  {
    Event e = new Event();
    ListView listview ;
    ArrayList<GetStaffBean> pb;
    // Handler
    DatabaseHelper handler = new DatabaseHelper(this);

    final DatabaseHelper db = new DatabaseHelper(AssignStaffActivity.this);

    SparseBooleanArray sparseBooleanArray=new SparseBooleanArray(100);

    ArrayList<String> staff_pretty = new ArrayList<String>();
    ArrayList<Integer> staff_id = new ArrayList<Integer>();

    ArrayList<Integer> assigned_staff = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_assign_staff);


        //make db call
        // SharedPreferences called just because I want to use the User-ID
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
        int userID=user.getId();

        e = (Event)getIntent().getSerializableExtra("EVENT");

        int eventID = e.getId();


        pb=db.getAllStaff();
        final String[] testArray1 =new String[pb.size()];
        final Integer[] testArray2 =new Integer[pb.size()];

        int i=0;
        for(GetStaffBean p : pb ){
            testArray1[i]= p.getFirstName();
            testArray2[i] = p.getId();
            i++;
        }

        List<String> testList = Arrays.asList(testArray1);

        listview = (ListView)findViewById(R.id.assign_staff_list_view);

        //////////////////////////////////////////////////////////////////////////////////populate staff_pretty and staff_id here

        staff_id.add(1);
        staff_id.add(2);
        staff_id.add(3);
        staff_id.add(4);
        staff_id.add(5);
        staff_id.add(6);
        staff_id.add(7);
        staff_id.add(8);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (AssignStaffActivity.this,
                        R.layout.custom_list_view,
                         testList );



        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                //listview.getChildAt(position).setBackgroundResource(R.drawable.rectangle_selected);

                for(int i = 0;i<listview.getChildCount();i++)
                {

                    if (listview.isItemChecked(position))
                    {
                        listview.getChildAt(position).setBackgroundResource(R.drawable.rectangle_selected);
                    }
                    else listview.getChildAt(position).setBackgroundResource(R.drawable.rectangle);
                }

            }
        });

        // Pick a Date Button
        final Button submit_button = (Button) findViewById(R.id.assign_staff_submit_id);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0;i<listview.getChildCount();i++)
                {

                    if (listview.isItemChecked(i))
                    {
                     assigned_staff.add(staff_id.get(i));
                    }

                }

                /////////////////////////////////////////////////////////////////////for loop to add staffs to assignment table

                boolean wasAccepted = assignStaffdB(assigned_staff, e.getId());

                if (wasAccepted)
                {
                    finish();
                }

                finish();
            }
        });


    }

  public boolean  assignStaffdB(ArrayList<Integer> assigned_staff, int eventID)
  {
      boolean wasAccepted=false;
      /////////////////////////for loop to assign staff for all in assigned-staff arrayList
      return wasAccepted;
  }


}