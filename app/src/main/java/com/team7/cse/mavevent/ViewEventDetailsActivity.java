package com.team7.cse.mavevent;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ViewEventDetailsActivity extends AppCompatActivity {

    DatabaseHelper handler;
    ListView listView;
    Event e = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_details);

        // Let's get serializable data
        e = (Event)getIntent().getSerializableExtra("EVENT");
        String eventname = e.getName();
        Integer eventId = e.getId();

        // Database Helper
        final DatabaseHelper db = new DatabaseHelper(ViewEventDetailsActivity.this);

        // SharedPreferences called just because I want to use the User-ID
        SharedPreferences mPrefs = getSharedPreferences("GLOBAL",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        UserBaseModel user = gson.fromJson(json, UserBaseModel.class);
        int userID=user.getId();


        String[] updatedView = db.viewFinalSumm(eventId);
        String[] updatedView1 = db.viewFinalSumm2(eventId);
        updateTextView(updatedView, updatedView1);

    }



    public void updateTextView(String[] updatedView, String[] updatedView1) {


        TextView textViewa = (TextView) findViewById(R.id.detail_attendees1);
        TextView textViewb = (TextView) findViewById(R.id.detail_hall1);
        TextView textViewc = (TextView) findViewById(R.id.detail_formality1);
        TextView textViewd = (TextView) findViewById(R.id.detail_bookedby1);
        TextView textViewe = (TextView) findViewById(R.id.detail_venue1);
        TextView textViewf = (TextView) findViewById(R.id.date_when1);   // Just Date
        TextView textViewg = (TextView) findViewById(R.id.detail_duration1); // Just Hour
        TextView textViewh = (TextView) findViewById(R.id.detail_staffass1);
        TextView textViewi = (TextView) findViewById(R.id.detail_price1);
        TextView textViewj = (TextView) findViewById(R.id.detail_meal_type1);
        TextView textViewk = (TextView) findViewById(R.id.detail_alcohol1);


        /**
         *
         *
         * Please do some calculation for Duration and parsing Start Date
         *
         * Also Cost Calculation
         *
         **/
        ArrayList<Integer> lol = parseTime(updatedView[6]);
        int capacity = Integer.parseInt(updatedView[7]);
        int duration = parseTime(updatedView[6]).get(3) - parseTime(updatedView[5]).get(3);
        //8 is alcohol
        //9 is meal type
        int alcoholCost = Integer.parseInt(updatedView[8]) * Integer.parseInt(updatedView[0]);
        int mealCost=0;
        String mealType="";
        switch(Integer.parseInt(updatedView[9]))
        {
            case 1:
                {
                    mealCost = 8;
                    mealType = "Breakfast";
                } break;
            case 2:
                {
                    mealCost = 12;
                    mealType = "Lunch";
                } break;
            case 3:
            {
                mealCost = 18;
                mealType = "Dinner";
            } break;
        }
        mealCost *= Integer.parseInt(updatedView[0]);
        int formalCost = Integer.parseInt(updatedView[2]) * Integer.parseInt(updatedView[0]);
        String hasAlcohol="";
        if (Integer.parseInt(updatedView[8])==1)
        {
            hasAlcohol = "Has Alcohol";
        }
        else
        {
            hasAlcohol = "Doesn't Have Alcohol";
        }
        String isFormal = "";
        if (Integer.parseInt(updatedView[2])==1)
        {
            isFormal = "Formal";
        }
        else
        {
            isFormal = "Informal";
        }
        textViewa.setText(updatedView[0]);
        textViewb.setText(updatedView[1]);
        textViewc.setText(isFormal);
        textViewd.setText(updatedView[3]);
        textViewe.setText(updatedView[4]);
        textViewf.setText(updatedView[5]);
        textViewg.setText(Integer.toString(duration));
        textViewh.setText(updatedView1[0]);
        textViewi.setText(Double.toString((duration * 2 * capacity + alcoholCost + mealCost + formalCost)));    //Just for sake of testing
        textViewj.setText(mealType);
        textViewk.setText(hasAlcohol);


    }
    ArrayList<Integer> parseTime (String time)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String PATTERN = "yyyy-MM-dd' 'hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = sdf.parse(time);
            list.add(date.getYear());
            list.add(date.getMonth());
            list.add(date.getDay());
            list.add(date.getHours());
            list.add(date.getMinutes());
            return list;
        } catch (ParseException e1) {
            //
        }

        return list;

    }

}