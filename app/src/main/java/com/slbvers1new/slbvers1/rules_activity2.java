package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.slbvers1new.slbvers1.rules_activity;


public class rules_activity2 extends AppCompatActivity {

    private Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules2);

        TextView Textview2 = findViewById(R.id.Textview2);
        Textview2.setText(R.string.SlugBug_rulespg2);

        button7 = findViewById(R.id.button7);

        button7.setOnClickListener(v -> {
            Intent intent =
                    new Intent(rules_activity2.this, rules_activity.class);
            startActivity(intent);
        });
    }
}
