package com.rbc.yelp.services.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rbc.yelp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        launchHomeActivity();
    }

    private void launchHomeActivity(){

        Handler mHandler=new Handler();
        Runnable launcherRunnable = () -> {

            Intent i = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(i);
            finish();

        };

        mHandler.postDelayed(launcherRunnable,2000);

    }

}