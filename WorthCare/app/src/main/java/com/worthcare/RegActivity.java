package com.worthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class RegActivity extends AppCompatActivity {

    private EditText userFullname, userEmail, userPassword, userCPassword, userHeight, userWeight;
    private RadioButton genderMale, genderFemale;
    String gender = "";
    private ProgressBar loadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;
    private Button next;
    private ConstraintLayout primaryLayout1, primaryLayout2;
    private TextView login_btn;
    FirebaseDatabase userDB; //Optional till 19Nov2019
    DatabaseReference userDBRef;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(RegActivity.this, NavDrawer.class));
            updateUI();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //Casting Views
        userFullname = findViewById(R.id.user_fullname);
        userEmail = findViewById(R.id.user_email_id);
        userPassword = findViewById(R.id.user_password);
        userCPassword = findViewById(R.id.user_cpassword);
        userHeight = findViewById(R.id.et_height);
        userWeight = findViewById(R.id.et_weight);
        genderMale = findViewById(R.id.g_male);
        genderFemale = findViewById(R.id.g_female);
        loadingProgress = findViewById(R.id.reg_progress_bar);
        regBtn = findViewById(R.id.reg_btn);
        loadingProgress.setVisibility(View.INVISIBLE);
        next = findViewById(R.id.next_btn);
        primaryLayout1 = findViewById(R.id.formLayout1);
        primaryLayout2 = findViewById(R.id.formLayout2);
        login_btn = findViewById(R.id.tv_login_btn);

        mAuth = FirebaseAuth.getInstance();
        userDBRef = FirebaseDatabase.getInstance().getReference("Users");

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this, LoginActivity.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryLayout1.setVisibility(View.INVISIBLE);
                primaryLayout2.setVisibility(View.VISIBLE);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String name = userFullname.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String cpassword = userCPassword.getText().toString();
                final String user_height = userHeight.getText().toString();
                final String user_weight = userWeight.getText().toString();

                if(genderMale.isChecked())
                {
                    gender = "Male";
                }
                if(genderFemale.isChecked())
                {
                    gender = "Female";
                }
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty() || user_height.isEmpty() || user_weight.isEmpty())
                {
                    showDialogue("Please check all the fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    users info = new users(
                                            name, email, gender, user_height, user_weight
                                    );
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showDialogue("Registration Completed");
                                            updateUI();
                                        }
                                    });


                                } else {

                                }

                                // ...
                            }
                        });

            }
        });

    }


    private void updateUI()
    {
        Intent NavDrawer = new Intent(getApplicationContext(), NavDrawer.class);
        startActivity(NavDrawer);
        finish();
    }

    private void showDialogue(String message)
    {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }

}
