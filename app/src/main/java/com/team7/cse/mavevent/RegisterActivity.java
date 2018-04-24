package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class RegisterActivity extends AppCompatActivity implements OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Just Testing the stuff out
        final Button regButton = (Button) findViewById(R.id.new_password_id);
        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent catereractIntent = new Intent(RegisterActivity.this, UserActivity.class);
                RegisterActivity.this.startActivity(catereractIntent);
            }

        });
        Spinner user_spinner = (Spinner) findViewById(R.id.user_spinner_id);
        user_spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Caterer");
        categories.add("User");
        categories.add("Staff");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        user_spinner.setAdapter(dataAdapter);
    }

    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }

}
