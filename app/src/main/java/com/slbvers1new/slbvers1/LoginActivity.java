package com.slbvers1new.slbvers1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.slbvers1new.slbvers1.MainActivity;
import com.slbvers1new.slbvers1.Registration;
import com.slbvers1new.slbvers1.Request_Password;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {


    TextView signupText;

    Button SignUp;
    Button home4;
    private EditText password_toggle;
    public static EditText username;

    private TextView Info;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private int counter = 5;

    Button LoginButton;
    Button Forgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_page);

        username = findViewById(R.id.username);
        password_toggle = findViewById(R.id.password_toggle);

        signupText = (TextView) findViewById(R.id.signupText);

        LoginButton = findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTheCredentials1();
                validate();
            }
        });


        SignUp = (Button) findViewById(R.id.SignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Registration.class);
                startActivity(intent);
            }
        });
        home4 = (Button) findViewById(R.id.home4);
        home4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });
        Forgot = findViewById(R.id.Forgot);
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Request_Password.class);
                startActivity(intent);
            }
        });
        // };
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


    }

    private void checkTheCredentials1() {

        String user = username.getText().toString();
        String password1 = password_toggle.getText().toString();

        if (user.isEmpty() || user.length() < 7 || !user.contains("@")) {
            showError(username, "Your username is not valid!");
        } else if (password1.isEmpty() || password1.length() < 10) {
            showError(password_toggle, "Your Password is not Valid");
        }
    }

    private void showError(EditText username, String s) {
        username.setError(s);

    }

    @Override
    public void onStart() {
        super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    private void validate() {
        String user = username.getText().toString();
        String password1 = password_toggle.getText().toString();
        String tag = null;
        String logging = null;
        Info = findViewById(R.id.Info);
        if (username == null || user.isEmpty()) {
            // Toast message to the user.
            Toast.makeText(this, "Please Enter a valid Email", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            }
            else{
        progressDialog.setMessage("Checking your credentials");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(user, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();


                   // Check if user is signed in (non-null) and update UI accordingly.
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    // enter user Data into the firebase Realtime Database.

                    //Extract user reference from database for "register users"
                    DatabaseReference SlugBugUsers = FirebaseDatabase.getInstance("https://slbvers1-default-rtdb.firebaseio.com/").getReference("Hero");
                    SlugBugUsers.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, After_Logged_in.class));
                        }

                    });

                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("The number of attempts to log in is:" + counter);
                    progressDialog.dismiss();
                    if (counter == 0) {
                        LoginButton.setEnabled(false);
                    }
                }
            }
        });
    }
}}















