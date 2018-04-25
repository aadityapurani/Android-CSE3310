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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class RegisterActivity extends AppCompatActivity implements OnItemSelectedListener{

    EditText emailSection, passwordSection, fnameSection, lnameSection, unameSection, utaidSection, addressSection, phoneSection;
    String val1;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailSection = (EditText) findViewById(R.id.emailSection);
        passwordSection = (EditText) findViewById(R.id.passwordSection);
        fnameSection = (EditText) findViewById(R.id.fnameSection);
        lnameSection = (EditText) findViewById(R.id.lnameSection);
        unameSection = (EditText) findViewById(R.id.unameSection);
        utaidSection = (EditText) findViewById(R.id.utaidSection);
        addressSection = (EditText) findViewById(R.id.addressSection);
        phoneSection = (EditText) findViewById(R.id.phoneSection);

        Spinner user_spinner = (Spinner) findViewById(R.id.user_spinner_id);
        user_spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Caterer");
        categories.add("User");
        categories.add("Staff");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        user_spinner.setAdapter(dataAdapter);


        // Just Testing the stuff out
        final Button regButton = (Button) findViewById(R.id.new_password_id);
        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (isInputValid(emailSection, passwordSection, unameSection)){
                    UserModel user = new UserModel();
                    user.setUserFName(fnameSection.getText().toString());
                    user.setUserLName(lnameSection.getText().toString());
                    user.setUserName(unameSection.getText().toString());
                    user.setUserAddress(addressSection.getText().toString());
                    user.setUserPhone(phoneSection.getText().toString());
                    user.setUserEmail(emailSection.getText().toString());
                    user.setUserPassword(passwordSection.getText().toString());
                    val1 = utaidSection.getText().toString();
                    int finalval1 = Integer.parseInt(val1);
                    user.setUserUta(finalval1);
                    user.setUserType(pos);
                    DatabaseHelper handler = new DatabaseHelper(RegisterActivity.this);
                    handler.addNewUser(user);
                    Toast.makeText(RegisterActivity.this, "Registration succesful", Toast.LENGTH_LONG).show();
                    finish();
                }


                //Intent catereractIntent = new Intent(RegisterActivity.this, UserActivity.class);
                //RegisterActivity.this.startActivity(catereractIntent);
            }

        });

    }

    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        pos = position;
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }

    private boolean isInputValid(EditText unameSection, EditText passwordSection, EditText emailSection){
        boolean status = true;

        if (emailSection.getText().toString().length() < 2) {
            emailSection.setError("Please enter valid email ID");
            status = false;
        }

        if (passwordSection.getText().toString().length() < 2) {
            passwordSection.setError("Please enter valid password");
            status = false;
        }

        if (unameSection.getText().toString().length() < 4){
            unameSection.setError("Please Enter valid Username");
            status = false;
        }

        return status;

    }

}