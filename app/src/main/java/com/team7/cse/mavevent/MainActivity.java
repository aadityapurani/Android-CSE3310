package com.team7.cse.mavevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team7.cse.mavevent.DatabaseHelper;
import com.team7.cse.mavevent.DatabaseManager;

//import static com.team7.cse.mavevent.MainActivity.user_type.CATERER;
//import static com.team7.cse.mavevent.MainActivity.user_type.STAFF;
//import static com.team7.cse.mavevent.MainActivity.user_type.USER;

public class MainActivity extends AppCompatActivity {

    /**
     boolean loginSuccessful = false;
     public enum user_type {
     CATERER, USER, STAFF
     }

     user_type dbUser;
     protected boolean login(String user, String pass)
     {
     return true;
     }
     protected user_type get_user_from_DB()
     {
     return CATERER;
     }
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper handler = new DatabaseHelper(MainActivity.this);
        //UserModel user = handler.retrieveUser(login_user, login_pass);
        handler.updateData();

        // It's just a workaround UI Demo to link the hyperlink to Register Page


        final EditText user_field   = (EditText)findViewById(R.id.login_username_id);
        final EditText pass_field   = (EditText)findViewById(R.id.login_password_id);

        final TextView loginLink = (TextView) findViewById(R.id.login_login_id);
        loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String login_user = user_field.getText().toString();
                String login_pass = pass_field.getText().toString();
                //put code to auth from database here...

                if(isInputValid(user_field, pass_field)){
                    // DatabaseHelper handler = new DatabaseHelper(MainActivity.this);
                    // check if user is in database
                    UserBaseModel currentPerson;
                    //x = new User();
                    int userType = handler.isValidUser(login_user,login_pass);
//                    handler.updateData();
                    /*
                    helpful stuff
                     */

                    if(userType!=-1){
                        Toast.makeText(MainActivity.this, "Login Successful ", Toast.LENGTH_LONG).show();
                        //int hh = user.getUserType();
                        switch(userType){
                            case 0:
                            {
                                currentPerson = handler.getCaterer(login_user,login_pass);
                                Intent loginIntent = new Intent(MainActivity.this, CatererActivity.class);
                                MainActivity.this.startActivity(loginIntent);
                                break;
                            }
                            case 1:
                            {
                                currentPerson= handler.getUser(login_user,login_pass);
                                Intent loginIntent = new Intent(MainActivity.this, UserActivity.class);
                                MainActivity.this.startActivity(loginIntent);
                                break;
                            }
                            case 2:
                            {
                                currentPerson = handler.getStaff(login_user,login_pass);
                                Intent loginIntent = new Intent(MainActivity.this, StaffActivity.class);
                                MainActivity.this.startActivity(loginIntent);
                                break;
                            }

                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_LONG).show();
                    }
                }


                /*
                loginSuccessful = login(login_user, login_pass);
                if (loginSuccessful)
                {
                    //get user type from dB
                    dbUser = get_user_from_DB();
                    switch(dbUser)
                    {
                        case CATERER:
                        {
                            Intent loginIntent = new Intent(MainActivity.this, CatererActivity.class);
                            MainActivity.this.startActivity(loginIntent);
                            break;
                        }
                        case USER:
                        {
                            Intent loginIntent = new Intent(MainActivity.this, UserActivity.class);
                            MainActivity.this.startActivity(loginIntent);
                            break;
                        }
                        case STAFF:
                        {
                            Intent loginIntent = new Intent(MainActivity.this, StaffActivity.class);
                            MainActivity.this.startActivity(loginIntent);
                            break;
                        }
                    }

                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Login Failed...";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                */

            }

        });

        final TextView registerLink = (TextView) findViewById(R.id.login_register_id);
        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }

        });

        final TextView resetLink = (TextView) findViewById(R.id.reset_password_id);
        resetLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent reset_password_Intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                MainActivity.this.startActivity(reset_password_Intent);
            }

        });
    }

    // Just Another Validator
    private boolean isInputValid(EditText unameSection, EditText passwordSection){
        boolean status = true;

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