package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slbvers1new.slbvers1.slug_bub_reward;
import com.slbvers1new.slbvers1.MainActivity;
import com.slbvers1new.slbvers1.ReadWriteUserDetails;

public class Registration extends AppCompatActivity {



    Button home3;
    Button RegMe;
    private FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;

    private EditText UsernameBox, PasswordBox1, PasswordBox2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        UsernameBox = findViewById(R.id.FirstName);
        PasswordBox1 = findViewById(R.id.LastName);
        PasswordBox2 = findViewById(R.id.PasswordBox2);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        RegMe = findViewById(R.id.SlugBug_ID);

        RegMe.setOnClickListener(v -> {

            if (checkTheCredentials()) {
                registerClient();
            }

        });

        home3 = (Button) findViewById(R.id.home3);
        home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });






    }

    private boolean checkTheCredentials() {

        String username = UsernameBox.getText().toString().trim();
        String pass1 = PasswordBox1.getText().toString();
        String pass2 = PasswordBox2.getText().toString();

        if (username.isEmpty()
                || username.length() < 7
                || !username.contains("@")) {

            showError(
                    UsernameBox,
                    "Your username is not valid!"
            );

            return false;

        } else if (pass1.isEmpty() || pass1.length() < 10) {

            showError(
                    PasswordBox1,
                    "Your Password is not Valid"
            );

            return false;

        } else if (pass2.isEmpty() || !pass2.equals(pass1)) {

            showError(
                    PasswordBox2,
                    "Your Passwords do not Match"
            );

            return false;
        }

        return true;
    }

    private void showError(EditText UsernameBox, String s) {
        UsernameBox.setError(s);

    }
    private void registerClient(){
           String username = UsernameBox.getText().toString();
           String pass1 = PasswordBox1.getText().toString();

        String tag = null;
        String logging = tag;
       if(UsernameBox == null || username.isEmpty()){
           Log.e("Registration", "Username is empty or null in registerClient");
           Toast.makeText(this, "Username Cannot be empty!",Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(Registration.this, Registration.class);
          startActivity(intent);
       }
       else {


        mAuth.createUserWithEmailAndPassword(username, pass1)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(null,"signInWithEmail:success");
                Toast.makeText(Registration.this, "You have now been registered.",Toast.LENGTH_SHORT).show();




                    // enter user Data into the firebase Realtime Database.

                ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(username);
                    //Extract user reference from database for "register users"
                    DatabaseReference SlugbugUsers = FirebaseDatabase.getInstance("https://slbvers1-default-rtdb.firebaseio.com/").getReference("Hero");
                    SlugbugUsers.child(firebaseAuth.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

            } else {
                // If sign in fails, display a message to the user.
                Log.w(null, "signInWithEmail:failure", task.getException());
                Toast.makeText(Registration.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
               ;

            }
        }
    });

        }}}



