package com.slbvers1new.slbvers1;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.slbvers1new.slbvers1.rules_activity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.slbvers1new.slbvers1.After_Logged_in;
import com.slbvers1new.slbvers1.LoginActivity;
import com.slbvers1new.slbvers1.dedication_page_activity;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    Button button;

    Button button1;

    Button button2;

    Button button3;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_main);

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

        ActivityCompat.requestPermissions(
                this,
                new String[]{RECORD_AUDIO},
                PERMISSION_GRANTED
        );

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // your existing button code continues here...
            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, rules_activity.class);
                    startActivity(intent);
                    stopService(new Intent(MainActivity.this, MainActivity.class));
                }
            });
        button1 = (Button) findViewById(R.id.Lets_Go);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseApp.initializeApp(MainActivity.this);
                FirebaseUser currentUser = mAuth.getCurrentUser();
                // checking to see if the user is logged in.
                if (currentUser != null) {
                    Intent intent = new Intent(MainActivity.this, After_Logged_in.class);
                    startActivity(intent);
                    stopService(new Intent(MainActivity.this, MainActivity.class));
                    // go to the log in page to get logged in if not logged in.
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        // this takes the user to the dedication page
        button2 = (Button) findViewById(R.id.button2d);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dedication_page_activity.class);
                startActivity(intent);
                stopService(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        button3 = (Button) findViewById(R.id.button3E);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopService(new Intent(MainActivity.this, MainActivity.class));

            }
        });
        // log out
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(MainActivity.this, "You have now logged out!", Toast.LENGTH_SHORT).show();
            }
        });





    }}