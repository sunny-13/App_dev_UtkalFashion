package com.appdev.utkalfashion;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.app_icon);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        final long period = 20;
        timer=new Timer();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    else{
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    // close this activity
                    SplashActivity.this.finish();
                }
            }
        }, 0, period);

//        new Handler().postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
//                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        }
//                        else{
//                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                        }
//                        finish();
//                    }
//                }, 4000
//        );
    }
}