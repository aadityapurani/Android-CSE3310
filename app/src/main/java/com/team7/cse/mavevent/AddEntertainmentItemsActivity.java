package com.team7.cse.mavevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.String;

public class AddEntertainmentItemsActivity extends AppCompatActivity {

    Event e = new Event();
    int quantity;
    String item;
    double cost;
    boolean wasValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_entertainment_items);
        final EditText name_field   = (EditText)findViewById(R.id.add_resources_name_id);
        final EditText quantity_field   = (EditText)findViewById(R.id.add_resources_quantity_id);
        final EditText cost_field   = (EditText)findViewById(R.id.add_resources_cost_id);



        // Submit Button
        Button add_resources_submit_button = (Button) findViewById(R.id.add_resources_submit_id);
        add_resources_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = name_field.getText().toString();

                if (!item.matches(""))
                {


                }
                else
                {
                    name_field.setError("Please enter a valid name");
                }
                try
                {
                    quantity = Integer.parseInt(quantity_field.getText().toString());

                }
                catch(Exception e)
                {
                    quantity_field.setError("Please enter a valid quantity");
                }
                try
                {
                    cost = Double.parseDouble(cost_field.getText().toString());
                }
                catch (Exception e)
                {
                    cost_field.setError("Please enter a valid cost");
                }
                e = (Event)getIntent().getSerializableExtra("EVENT");
                int eventID = e.getId();
                wasValid = addEnt(eventID, cost, item, quantity);
                if (wasValid) finish();

            }
        });

        }

    public boolean addEnt(int eventID, double cost, String name, int quantity)
    {
        boolean wasAccepted = false;
        final DatabaseHelper db = new DatabaseHelper(AddEntertainmentItemsActivity.this);
        //wasAccepted = db.requestEvent(attendees, mealType, comboDate, comboDate2, isAlcohol, formal, userID, eventName, eventCategory);
        wasAccepted = db.addNewEntertainmentItem(name,quantity,cost,eventID);
        return wasAccepted;
    }

}