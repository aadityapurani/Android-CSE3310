package com.team7.cse.mavevent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.team7.cse.mavevent.MainActivity.user_type.CATERER;
import static com.team7.cse.mavevent.MainActivity.user_type.STAFF;
import static com.team7.cse.mavevent.MainActivity.user_type.USER;

public class MainActivity extends AppCompatActivity {
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
        return STAFF;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

}
