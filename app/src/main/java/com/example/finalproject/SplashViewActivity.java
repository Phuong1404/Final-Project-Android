package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashViewActivity extends AppCompatActivity {

    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
        //getSupportActionBar().hide();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashViewActivity.this,StartScreenActivity.class);
                startActivity(i);
                finish();
            }
        },1000);

    }
}