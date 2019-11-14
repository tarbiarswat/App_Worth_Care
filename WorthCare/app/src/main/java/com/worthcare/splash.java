package com.worthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class splash extends AppCompatActivity {
    private TextView wc_title;
    private ImageView wc_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.splash);
        wc_title = (TextView) findViewById(R.id.wc_title);
        wc_logo = (ImageView) findViewById(R.id.wc_logo);
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.splashanim);
        wc_title.startAnimation(anim1);
        wc_logo.startAnimation(anim1);
        final Intent i = new Intent(this,RegActivity.class);
        Thread time = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(i);
                    finish();
                }
            }
        };


        time.start();

    }
}