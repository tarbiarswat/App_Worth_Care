package com.worthcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView gotoRegActivity;
    private EditText userEmail, userPassword;
    private Button loginBtn;
    private ProgressBar loginprogressbar;
    private FirebaseAuth mAuth;

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

        userEmail = findViewById(R.id.user_email_id);
        userPassword = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.login_btn);
        loginprogressbar = findViewById(R.id.login_progress_bar);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setVisibility(View.INVISIBLE);
                loginprogressbar.setVisibility(View.VISIBLE);
                String email=userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    showDialogue("Please enter your email");
                    loginBtn.setVisibility(View.VISIBLE);
                    loginprogressbar.setVisibility(View.INVISIBLE);
                }
                if(TextUtils.isEmpty(password))
                {
                    showDialogue("Please enter your password");
                    loginBtn.setVisibility(View.VISIBLE);
                    loginprogressbar.setVisibility(View.INVISIBLE);
                }
                if(password.length()<6)
                {
                    showDialogue("Password has to be at least 6 characters");
                    loginBtn.setVisibility(View.VISIBLE);
                    loginprogressbar.setVisibility(View.INVISIBLE);
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    showDialogue("Login Successful");
                                    updateUI();
                                } else {
                                    showDialogue("Invalid Email and Password");
                                    loginBtn.setVisibility(View.VISIBLE);
                                    loginprogressbar.setVisibility(View.INVISIBLE);

                                }
                            }
                        });
            }
        });

    }
    private void showDialogue(String message)
    {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
    private void updateUI()
    {
        Intent NavDrawer = new Intent(getApplicationContext(), NavDrawer.class);
        startActivity(NavDrawer);
        finish();
    }
}
