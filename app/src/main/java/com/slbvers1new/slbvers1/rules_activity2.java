package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.slbvers1new.slbvers1.rules_activity;


public class rules_activity2 extends AppCompatActivity {


    Button button7;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules2);
        final TextView Textview2 = (TextView)findViewById(R.id.Textview2);
        Textview2.setText(R.string.SlugBug_rulespg2);

        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( getApplicationContext(), rules_activity.class);
                startActivity(intent);
            }
        });
    }
    }
