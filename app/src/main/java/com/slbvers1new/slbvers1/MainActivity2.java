package com.slbvers1new.slbvers1;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;


import com.slbvers1new.slbvers1.SlugBugScore;
import com.slbvers1new.slbvers1.rules_activity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.slbvers1new.slbvers1.After_Logged_in;
import com.slbvers1new.slbvers1.LoginActivity;
import com.slbvers1new.slbvers1.dedication_page_activity;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference SlugBugUser;
    Button button;

    Button Lets_Go;

    Button button2;

    Button button3;
    Button Score;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_main2);

        View root = findViewById(R.id.constraintLayout);

        ViewCompat.setOnApplyWindowInsetsListener(root, (v, windowInsets) -> {

            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
            );

            v.setPadding(
                    insets.left,
                    insets.top,
                    insets.right,
                    insets.bottom
            );

            return windowInsets;
        });
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, rules_activity.class);
                startActivity(intent);
                stopService(new Intent(MainActivity2.this, MainActivity2.class));
            }
        });
        Lets_Go = (Button) findViewById(R.id.Lets_Go);
        Lets_Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseApp.initializeApp(MainActivity2.this);
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent intent = new Intent(MainActivity2.this, After_Logged_in.class);
                    startActivity(intent);
                    stopService(new Intent(MainActivity2.this, MainActivity2.class));
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        button2 = (Button) findViewById(R.id.button2d);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, dedication_page_activity.class);
                startActivity(intent);
                stopService(new Intent(MainActivity2.this, MainActivity2.class));
            }
        });
        button3 = (Button) findViewById(R.id.button3E);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finishAffinity();



            }
        });
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(MainActivity2.this, "You have now logged out!", Toast.LENGTH_SHORT).show();
            }
        });
        Score = findViewById(R.id.Score1);
        Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity2.this, SlugBugScore.class);
               startActivity(intent);
             //  Intent intent1= new Intent(MainActivity2.this, slug_bub_reward.class);
             //  stopService(intent1);

                }





        });


    }
}