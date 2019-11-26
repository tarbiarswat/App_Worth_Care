package com.worthcare;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    CustomProgressBar pb;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pb = (CustomProgressBar) findViewById(R.id.pb);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
        startAnimation();
    }

    private void startAnimation() {
        ProgressBarAnimation localProgressBarAnimation = new ProgressBarAnimation(0.0F, 75.0F);
        localProgressBarAnimation.setInterpolator(new OvershootInterpolator(0.5F));
        localProgressBarAnimation.setDuration(4000L);
        pb.startAnimation(localProgressBarAnimation);
    }

    private class ProgressBarAnimation extends Animation {
        private float from;
        private float to;

        public ProgressBarAnimation(float from, float to) {
            this.from = from;
            this.to = to;
        }

        protected void applyTransformation(float paramFloat, Transformation paramTransformation) {
            super.applyTransformation(paramFloat, paramTransformation);
            float f = this.from + paramFloat * (this.to - this.from);
            pb.setProgress((int) f);
        }
    }
}
