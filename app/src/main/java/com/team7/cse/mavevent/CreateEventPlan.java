package com.team7.cse.mavevent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;


public class CreateEventPlan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    List<String> meals;
    int rowSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_plan);
        final Calendar activityCalendar= Calendar.getInstance();


        final EditText attendees_field   = (EditText)findViewById(R.id.cep_attendees_id);

        //////////////////////////////////////////////////////////////////////////Spinner for meal type
        Spinner cep_meal_spinner = (Spinner) findViewById(R.id.cep_meal_id);
        cep_meal_spinner.setOnItemSelectedListener(this);

        meals = new ArrayList<String>();//this is the user-viewable name
        meals.add("Select a Meal");
        meals.add("Breakfast");
        meals.add("Lunch");
        meals.add("Dinner");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, meals);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        cep_meal_spinner.setAdapter(dataAdapter);

        ///////////////////////////////////////////////////////////////////////////////////////////////



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final DatePickerDialog  datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {

                activityCalendar.set(year, month, year);

            }

        }, activityCalendar.get(Calendar.YEAR), activityCalendar.get(Calendar.MONTH), activityCalendar.get(Calendar.DAY_OF_MONTH));

        // Pick a Date Button
        Button cep_pick_date_button = (Button) findViewById(R.id.cep_pick_date_id);
        cep_pick_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hour, int minute) {

                activityCalendar.set(Calendar.HOUR, hour);
                activityCalendar.set(Calendar.MINUTE, minute);

            }

        }, activityCalendar.get(Calendar.HOUR_OF_DAY), activityCalendar.get(Calendar.MINUTE), false);

        // Pick a Date Button
        Button cep_pick_time_button = (Button) findViewById(R.id.cep_pick_time_id);
        cep_pick_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog.show();

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final EditText duration_field   = (EditText)findViewById(R.id.cep_duration_id);
        Switch alcohol_switch = (Switch) findViewById(R.id.cep_alcohol_id);
        Switch formality_switch = (Switch) findViewById(R.id.cep_formality_id);

        // Submit Button
        Button cep_submit_button = (Button) findViewById(R.id.cep_submit_id);
        cep_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rowSelected = position;

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }
}

