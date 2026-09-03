package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.slbvers1new.slbvers1.MainActivity2;
import com.google.firebase.auth.FirebaseAuth;

public class dedication_page_activity extends AppCompatActivity {

    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dedication_page);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(R.string.Tribute_Annette);

        button5 = findViewById(R.id.button5);

        button5.setOnClickListener(v -> {
            Intent intent =
                    new Intent(dedication_page_activity.this, MainActivity2.class);
            startActivity(intent);
        });
    }
}