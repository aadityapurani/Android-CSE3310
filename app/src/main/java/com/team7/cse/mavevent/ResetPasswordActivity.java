package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Main - > Reset Password Page
        final Button reset_password_Button = (Button) findViewById(R.id.reset_pass_id);
        reset_password_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent reset_password_Intent = new Intent(ResetPasswordActivity.this, NewPasswordActivity.class);
                Intent reset_password_Intent = new Intent(ResetPasswordActivity.this, NewPasswordActivity.class);
                ResetPasswordActivity.this.startActivity(reset_password_Intent);
            }
        });
    }
}