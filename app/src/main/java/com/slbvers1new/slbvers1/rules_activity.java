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



    Button button4;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        final TextView textview = (TextView) findViewById(R.id.textview);
        textview.setText(R.string.SlugBug_rules);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        button6 =(Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rules_activity2.class);
                startActivity(intent);
            }
        });
    }
}