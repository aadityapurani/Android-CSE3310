package com.team7.cse.mavevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Just Testing the stuff out
        final Button regButton = (Button) findViewById(R.id.button2);
        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent catereractIntent = new Intent(RegisterActivity.this, CatererActivity.class);
                RegisterActivity.this.startActivity(catereractIntent);
            }

        });
    }

}
