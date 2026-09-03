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

import com.google.firebase.auth.FirebaseAuth;
import com.slbvers1new.slbvers1.MainActivity;

public class Request_Password extends AppCompatActivity {




    private EditText textView3;
    Button send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_password);

        String tag;

        textView3 = findViewById(R.id.textView3);
        String email = textView3.getText().toString();

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResetPlease();
            }
        });


                            }
    public void sendResetPlease(){

        textView3 = findViewById(R.id.textView3);
        String email = textView3.getText().toString();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(textView3 == null || email.isEmpty()){ Log.e("Email","Email is empty or null");
            Toast.makeText(this,"Please enter uour email",Toast.LENGTH_SHORT).show();
            return;}
        auth.sendPasswordResetEmail(email)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
                String tag = null;
                if (task.isSuccessful()){
                Log.d(null,"Email sent.");
                Toast.makeText(Request_Password.this,"An Email has been sent if you have an account!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Request_Password.this, MainActivity.class);
                    startActivity(intent);
                       }
                    }
    });
    }
}