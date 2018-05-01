package com.team7.cse.mavevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
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

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Main - > Reset Password Page
        final EditText pass_field   = (EditText)findViewById(R.id.unameSection);
        final Button reset_password_Button = (Button) findViewById(R.id.reset_pass_id);
        reset_password_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent reset_password_Intent = new Intent(ResetPasswordActivity.this, NewPasswordActivity.class);
                // send password
                String username = pass_field.getText().toString();
                // Create object of SharedPreferences.
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ResetPasswordActivity.this);
                //now get Editor
                SharedPreferences.Editor editor = sharedPref.edit();
                //put your value
                editor.putString("userName", username);

                //commits your edits
                editor.commit();
                /*Intent myintent=new Intent(IDNA.Info.this, GraphDiag.class).putExtra("<StringName>", username);
                startActivity(myintent);
                */
                Intent reset_password_Intent = new Intent(ResetPasswordActivity.this, NewPasswordActivity.class);
                ResetPasswordActivity.this.startActivity(reset_password_Intent);
            }
        });
    }
}