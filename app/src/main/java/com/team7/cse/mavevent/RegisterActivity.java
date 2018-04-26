package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.team7.cse.mavevent.DatabaseHelper;
import com.team7.cse.mavevent.User;

public class RegisterActivity extends AppCompatActivity implements OnItemSelectedListener{

    EditText emailSection, passwordSection, fnameSection, lnameSection, unameSection, utaidSection, addressSection, phoneSection;
    String val1;
    int pos;
    DatabaseHelper handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Creating Global Handler
         handler = new DatabaseHelper(this);

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

                Reg(handler);
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


    public void Reg(DatabaseHelper handler){

        UserModel user = new UserModel();

        String fname = fnameSection.getText().toString();
        String lname = lnameSection.getText().toString();
        String uname = unameSection.getText().toString();
        String address = addressSection.getText().toString();
        String phone = phoneSection.getText().toString();
        String email = emailSection.getText().toString();
        String password = passwordSection.getText().toString();

        boolean ready = true;

        if((isInputValid(unameSection, passwordSection, emailSection)) && (!fname.isEmpty()) && (!lname.isEmpty()) && (!uname.isEmpty()) &&  (!email.isEmpty()) && (!address.isEmpty()) && (!phone.isEmpty())){

            user.setUserFName(fname);
            user.setUserLName(lname);
            user.setUserName(uname);
            user.setUserAddress(address);
            user.setUserType(pos);
            user.setUserEmail(email);
            user.setUserPassword(password);

            if(phone.length() == 10) {
                user.setUserPhone(phone);
            }
            else{
                ready = false;
                Toast.makeText(RegisterActivity.this, "Phone Number should be of 10 digits", Toast.LENGTH_LONG).show();
            }

            val1 = utaidSection.getText().toString();
            if(val1.length() == 10) {
                int finalval1 = Integer.parseInt(val1);
                user.setUserUta(finalval1);
            }else{
                ready = false;
                Toast.makeText(RegisterActivity.this, "ID should be of 10 digits", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(RegisterActivity.this, "Empty Text Field(s)", Toast.LENGTH_LONG).show();
            ready = false;
        }

        if(ready){
            handler.addNewUser(user);
            Toast.makeText(RegisterActivity.this, "Registration succesful", Toast.LENGTH_LONG).show();
            finish();
        }
        if(!ready) {
            Toast.makeText(RegisterActivity.this, "Registration failure", Toast.LENGTH_LONG).show();
        }




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