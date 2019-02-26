package com.example.fitclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fitclub.Activities.StartActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ProgressBar progressBar = findViewById(R.id.progressBarId);
        TextView textView = findViewById(R.id.progressTextId);

        ProgressBarAnimation animation = new ProgressBarAnimation(this,progressBar,textView,0f,100f);
        animation.setDuration(SPLASH_TIME_OUT);
        progressBar.setAnimation(animation);

       // progressBar.setMax(100);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, StartActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },SPLASH_TIME_OUT);



    }
}
