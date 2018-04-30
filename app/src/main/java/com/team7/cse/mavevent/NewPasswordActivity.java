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

public class NewPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        // Main - > New Password Page
        final Button new_password_Button = (Button) findViewById(R.id.new_password_id);
        new_password_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_password_Intent = new Intent(NewPasswordActivity.this, MainActivity.class);
                NewPasswordActivity.this.startActivity(new_password_Intent);
            }
        });
    }
}