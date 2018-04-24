package com.team7.cse.mavevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // It's just a workaround UI Demo to link the hyperlink to Register Page

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }

        });

        final TextView loginLink = (TextView) findViewById(R.id.login_login_id);
        loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginIntent = new Intent(MainActivity.this, CatererActivity.class);
                MainActivity.this.startActivity(loginIntent);
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

}
