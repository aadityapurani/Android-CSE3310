package com.team7.cse.mavevent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
//import android.icu.util.Calendar;
import java.util.Calendar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.team7.cse.mavevent.DatabaseHelper;


public class CreateEventPlan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    List<String> meals;
    int rowSelected;
    String attendees;
    String duration;
    String mealType;
    String dateTime;
    String eventName;
    String eventCategory;
    int hasAlcohol = 0;
    int isFormal = 0;
    boolean setDate=false;
    boolean setTime=false;
    boolean validAttendees = false;
    boolean validDuration = false;
    boolean validMeal = false;
    boolean validName = false;
    boolean validCategory = false;
    DatabaseHelper handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_plan);
        final Calendar activityCalendar= Calendar.getInstance();

        // Handler
        handler = new DatabaseHelper(this);

        final EditText event_name_field   = (EditText)findViewById(R.id.cep_event_name_id);
        final EditText event_category_field   = (EditText)findViewById(R.id.cep_event_category_id);
        final EditText attendees_field   = (EditText)findViewById(R.id.cep_attendees_id);
        //////////////////////<M E A L - T Y P E - S P I N N E R//////////////////////////////////
        final Spinner cep_meal_spinner = (Spinner) findViewById(R.id.cep_meal_id);
        cep_meal_spinner.setOnItemSelectedListener(this);

        meals = new ArrayList<String>();//this is the user-viewable name
        meals.add("Select a Meal");
        meals.add("Breakfast");
        meals.add("Lunch");
        meals.add("Dinner");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, meals);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        cep_meal_spinner.setAdapter(dataAdapter);

        ////////////////////// M E A L - T Y P E - S P I N N E R> //////////////////////////////////


        /////////////////////// <D A T E - P I C K E R ////////////////////////////////////////////
        final DatePickerDialog  datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {

                activityCalendar.set(year, month, day);
                setDate = true;

            }

        }, activityCalendar.get(Calendar.YEAR), activityCalendar.get(Calendar.MONTH), activityCalendar.get(Calendar.DAY_OF_MONTH));

        // Pick a Date Button
        final Button cep_pick_date_button = (Button) findViewById(R.id.cep_pick_date_id);
        cep_pick_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();
                cep_pick_date_button.setBackgroundColor(0xFF3A7CE8);

            }
        });
        ////////////////////// D A T E - P I C K E R> /////////////////////////////////////////////

        ////////////////////// <T I M E - P I C K E R ////////////////////////////////////////////
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hour, int minute) {

                activityCalendar.set(Calendar.HOUR_OF_DAY, hour);
                activityCalendar.set(Calendar.MINUTE, minute);
                setTime = true;

            }

        }, activityCalendar.get(Calendar.HOUR_OF_DAY), activityCalendar.get(Calendar.MINUTE), false);

        // Pick a Date Button
        final Button cep_pick_time_button = (Button) findViewById(R.id.cep_pick_time_id);
        cep_pick_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog.show();
                cep_pick_time_button.setBackgroundColor(0xFF3A7CE8);

            }
        });
        /////////////////////// T I M E - P I C K E R> ///////////////////////////////////////////


        final EditText duration_field   = (EditText)findViewById(R.id.cep_duration_id);

        //////////////////////// <S W I T C H E S /////////////////////////////////////////////////
        Switch alcohol_switch = (Switch) findViewById(R.id.cep_alcohol_id);
        alcohol_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) hasAlcohol = 1;
                else hasAlcohol = 0;
                }
        });


        Switch formality_switch = (Switch) findViewById(R.id.cep_formality_id);
        formality_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) isFormal = 1;
                else isFormal = 0;

            }
        });
        //////////////////////// S W I T C H E S> //////////////////////////////////////////////////


        //////////////////////////// <S U B M I T //////////////////////////////////////////////////
        Button cep_submit_button = (Button) findViewById(R.id.cep_submit_id);
        cep_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setTime)
                {
                    cep_pick_time_button.setBackgroundColor(0xFFf44e42);
                }
                if (!setDate)
                {
                    cep_pick_date_button.setBackgroundColor(0xFFf44e42);
                }
                String hour = Integer.toString(activityCalendar.get(Calendar.HOUR_OF_DAY));
                String minute = Integer.toString(activityCalendar.get(Calendar.MINUTE));
                String day = Integer.toString(activityCalendar.get(Calendar.DAY_OF_MONTH));
                String month = Integer.toString(activityCalendar.get(Calendar.MONTH)+1);
                String year = Integer.toString(activityCalendar.get(Calendar.YEAR));
                String comboDate = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ":" + "00";
                String formal = Integer.toString(isFormal);

                if(event_name_field.getText().length() !=0)
                {
                    event_name_field.setError(null);
                    eventName = event_name_field.getText().toString();
                    validName = true;
                }
                else
                {
                    event_name_field.setError("You must pick a name");
                }

                if (event_category_field.getText().length() != 0)
                {
                    event_category_field.setError(null);
                    eventCategory = event_category_field.getText().toString();
                    validCategory = true;
                }
                else
                {
                    event_category_field.setError("Invalid Category");
                }
                ///////////////////// <<A T T E N D E E S  V A L I D A T I O N ////////////////////
                if (attendees_field.getText().length() !=0)
                {
                    try
                    {
                        Integer.parseInt(attendees_field.getText().toString());
                        attendees_field.setError(null);
                        attendees = attendees_field.getText().toString();
                        validAttendees = true;
                    }
                    catch (Exception e)
                    {
                        attendees_field.setError("Invalid number of attendees");
                    }
                }
                else
                {
                    attendees_field.setError("You must enter the number of attendees");
                }
                ///////////////////// A T T E N D E E S  V A L I D A T I O N>> ////////////////////

                ///////////////////// <<D U R A T I O N  V A L I D A T I O N ////////////////////
                if (duration_field.getText().length() !=0)
                {
                    try
                    {
                        Integer.parseInt(duration_field.getText().toString());
                        duration_field.setError(null);
                        duration = duration_field.getText().toString();
                        validDuration = true;
                    }
                    catch (Exception e)
                    {
                        duration_field.setError("Invalid duration");
                    }
                }
                else
                {
                    duration_field.setError("You must enter a duration");
                }

                if(rowSelected == 0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please select a meal";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    cep_meal_spinner.setBackgroundColor(0xFFf44e42);
                }
                else
                {
                    mealType = Integer.toString(rowSelected);
                    validMeal = true;
                }
                String isAlcohol = Integer.toString(hasAlcohol);



                //need userID from somewhere
                SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("User", "");
                UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
                String userID=user.getId()+"";//this is just temporary so that the request code works, remove it once you get the session version
                boolean wasAccepted=false;


                if (setDate && setTime && validAttendees && validDuration && validMeal && validCategory && validName) {
                    int hr_int = Integer.parseInt(hour);
                    int duration_int = Integer.parseInt(duration);
                    int final_int = hr_int + duration_int;
                    String comboDate2 = year + '-' + month + '-' + day + ' ' + Integer.toString(final_int) + ':' + minute + ":" + "00";
                    wasAccepted = createPlanDB(attendees, mealType, comboDate, comboDate2, isAlcohol, formal, userID, eventName, eventCategory, duration);//call to attempt to write to the database
                    if (wasAccepted) finish();

                    else {
                        //let them know
                        Context context = getApplicationContext();
                        CharSequence text = "There was an error making the request";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }

            }
        });
        //////////////////////////// S U B M I T> //////////////////////////////////////////////////
    }
    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rowSelected = position;
        view = findViewById(R.id.cep_meal_id);
        view.setBackgroundColor(0x00000000);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }


    //**************************WORK YOUR MAGIC HERE TO ACTUALLY FILL THE REQUEST*********************


    public boolean createPlanDB(String attendees, String mealType, String comboDate, String comboDate2, String isAlcohol, String formal, String userID, String eventName, String eventCategory, String duration)
    {
        boolean wasAccepted = false;
        final DatabaseHelper db = new DatabaseHelper(CreateEventPlan.this);
        wasAccepted = db.requestEvent(attendees, mealType, comboDate, comboDate2, isAlcohol, formal, userID, eventName, eventCategory);

//        wasAccepted =

        return wasAccepted;
    }


}

