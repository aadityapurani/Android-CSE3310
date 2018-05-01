package com.team7.cse.mavevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        final DatabaseHelper db = new DatabaseHelper(NewPasswordActivity.this);
        // Main - > New Password Page
        final EditText pass_field   = (EditText)findViewById(R.id.passwordSection);
        final Button new_password_Button = (Button) findViewById(R.id.new_password_id);
        new_password_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get password
                String new_pass = pass_field.getText().toString();

                //get username
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(NewPasswordActivity.this);
                String userName = sharedPref.getString("userName", "Not Available");

                //reset password
                db.resetPassword(userName, new_pass);

                //silly message
                Toast.makeText(NewPasswordActivity.this, "Password reset has been sent to your Mail!", Toast.LENGTH_LONG).show();


                Intent new_password_Intent = new Intent(NewPasswordActivity.this, MainActivity.class);
                NewPasswordActivity.this.startActivity(new_password_Intent);
            }
        });
    }
}