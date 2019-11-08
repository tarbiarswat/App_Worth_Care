package com.worthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView gotoRegActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gotoRegActivity = findViewById(R.id.goto_regactivity);
        gotoRegActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_gotoRegActivity = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent_gotoRegActivity);
            }
        });
    }
}
