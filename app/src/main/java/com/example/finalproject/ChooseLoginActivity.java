package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseLoginActivity extends AppCompatActivity {

    TextView LoginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login);

        LoginEmail=(TextView) findViewById(R.id.LoginEmail);

        LoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseLoginActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}