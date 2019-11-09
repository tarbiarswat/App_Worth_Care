package com.worthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegActivity extends AppCompatActivity {

    private EditText userFullname, userEmail, userPassword;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        userFullname = findViewById(R.id.user_fullname);
        userEmail = findViewById(R.id.user_email_id);
        userPassword = findViewById(R.id.user_password);
        loadingProgress = findViewById(R.id.reg_progress_bar);
        regBtn = findViewById(R.id.reg_btn);
        loadingProgress.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String name = userFullname.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty())
                {
                    showDialogue("Please check all the fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    createUserAccount(name, email, password);

                }

            }
        });

    }

    private void createUserAccount(final String name, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    showDialogue("Account Created Successfully");
                    updateUserInfo(name, mAuth.getCurrentUser());

                }
                else
                {
                    showDialogue("Account Creation Failed!" + task.getException().getMessage());
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void updateUserInfo(final String name, final FirebaseUser currentUser)
    {
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        currentUser.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    showDialogue("Registration Completed!");
                    updateUI();
                }
            }
        });
    }

    private void updateUI()
    {
        Intent Dashboard = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(Dashboard);
        finish();
    }

    private void showDialogue(String message)
    {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
}
