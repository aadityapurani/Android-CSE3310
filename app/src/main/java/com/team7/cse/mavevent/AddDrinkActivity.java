package com.team7.cse.mavevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

/**
 * Created by Casey on 4/29/18.
 */

public class AddDrinkActivity extends AppCompatActivity {

    String name;
    int quantity;

    boolean validName = false;
    boolean validQuantity = false;
    boolean wasValid = false;

    Event e = new Event();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_drink);
        final EditText name_field   = (EditText)findViewById(R.id.cad_name_id);
        final EditText quantity_field = (EditText) findViewById((R.id.cad_quantity_id));

        // Add Drinks - > submit
        final Button submit_Button = (Button) findViewById(R.id.cad_submit_id);
        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name_field.getText().length() !=0)
                {
                    //good
                    name_field.setError(null);
                    name = name_field.getText().toString();
                    validName = true;
                }
                else
                {
                    name_field.setError("Please enter a name");
                }
                if (quantity_field.getText().length() !=0)
                {
                   //good
                    quantity_field.setError(null);
                    try
                    {
                        quantity = Integer.parseInt(quantity_field.getText().toString());
                        validQuantity = true;
                    }
                    catch (Exception e)
                    {
                        quantity_field.setError("Invalid Quantity");
                    }
                }
                else
                {
                    quantity_field.setError("Please enter a quantity");
                }

                if (validName && validQuantity)
                {
                    //make db call
                    // SharedPreferences called just because I want to use the User-ID
                    SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = mPrefs.getString("User", "");
                    UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
                    int userID=user.getId();

                    e = (Event)getIntent().getSerializableExtra("EVENT");

                    int eventID = e.getId();

                    wasValid = addDrinkDB(userID, eventID, name, quantity);
                    if (wasValid) finish();

                }
            }
        });
    }
    public boolean addDrinkDB(int userID, int eventID, String name, int quantity)
    {
        boolean wasAccepted = false;
        final DatabaseHelper db = new DatabaseHelper(AddDrinkActivity.this);
        //wasAccepted = db.requestEvent(attendees, mealType, comboDate, comboDate2, isAlcohol, formal, userID, eventName, eventCategory);

        return wasAccepted;
    }
}
