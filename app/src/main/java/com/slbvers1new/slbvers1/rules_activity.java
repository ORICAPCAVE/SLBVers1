package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.slbvers1new.slbvers1.rules_activity2;
import com.slbvers1new.slbvers1.MainActivity2;

public class rules_activity extends AppCompatActivity {

    private Button button4;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        TextView textview = findViewById(R.id.textview);
        textview.setText(R.string.SlugBug_rules);

        button4 = findViewById(R.id.button4);
        button6 = findViewById(R.id.button6);

        button4.setOnClickListener(v -> {
            Intent intent =
                    new Intent(rules_activity.this, MainActivity2.class);
            startActivity(intent);
        });

        button6.setOnClickListener(v -> {
            Intent intent =
                    new Intent(rules_activity.this, rules_activity2.class);
            startActivity(intent);
        });
    }
}