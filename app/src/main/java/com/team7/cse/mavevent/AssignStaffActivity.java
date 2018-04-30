package com.team7.cse.mavevent;

import android.content.Context;
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

import java.util.ArrayList;

public class AssignStaffActivity extends AppCompatActivity  {
    Event e;
    ListView listview ;
    String[] ListViewItems = new String[] {
            "ListView ITEM-1",
            "ListView ITEM-2",
            "ListView ITEM-3",
            "ListView ITEM-4",
            "ListView ITEM-5",
            "ListView ITEM-6",
            "ListView ITEM-7",
            "ListView ITEM-8",
            "ListView ITEM-9",
            "ListView ITEM-10"

    };

    SparseBooleanArray sparseBooleanArray=new SparseBooleanArray(100);

    ArrayList<String> staff_pretty = new ArrayList<String>();
    ArrayList<Integer> staff_id = new ArrayList<Integer>();

    ArrayList<Integer> assigned_staff = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_assign_staff);

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
                         ListViewItems );



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