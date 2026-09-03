package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.slbvers1new.slbvers1.MainActivity2;
import com.slbvers1new.slbvers1.Play_Bug;


public class After_Logged_in extends AppCompatActivity {

    Button home2;
    Button playBug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_after_logged_in);

        View root = findViewById(R.id.Logged_in);

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

        home2 = findViewById(R.id.home2);
        home2.setOnClickListener(v -> {
            Intent intent =
                    new Intent(After_Logged_in.this, MainActivity2.class);
            startActivity(intent);
        });

        playBug = findViewById(R.id.playBug);
        playBug.setOnClickListener(v -> {
            Intent intent =
                    new Intent(After_Logged_in.this, Play_Bug.class);
            startActivity(intent);
        });
    }
}









