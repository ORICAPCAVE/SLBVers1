package com.slbvers1new.slbvers1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.slbvers1new.slbvers1.Scoreone.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.slbvers1new.slbvers1.LoginActivity.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import android.widget.ImageView;
import java.text.SimpleDateFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//import pl.droidsonroids.gif.GifImageView;
// this is the class that runs the reward for a slugbug it
// has a short Gif and sound byte.

public class slug_bub_reward extends AppCompatActivity {
    public static final String NEW_SLUG_BUG = "NewSlugBug";


   // private GifImageView slug_bug_reward;
   private ImageView slug_bug_reward;
    private Button Done;
    public MediaPlayer soundPlayer;
    private static DatabaseReference myRef;
    private FirebaseAuth mAuth;

    public String key, formatted, TAG,error, userID, uid, Users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slug_bub_reward);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        View root = findViewById(R.id.rewardRoot);

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
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        slug_bug_reward = findViewById(R.id.slug_bug2_reward);
        slug_bug_reward.post(() -> {

            Drawable drawable = slug_bug_reward.getDrawable();

            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        });
        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.slugbugreward);
        key = FirebaseDatabase.getInstance().getReference(mAuth.getUid()).getKey();
        myRef = FirebaseDatabase.getInstance().getReference(userID);

        soundPlayer.start();



        Done = (Button) findViewById(R.id.Done);
        Done.setOnClickListener(v -> {

            Log.d("SLUGBUG_TEST", "DONE BUTTON CLICKED");

            Date d = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd");
            String ND = sdf.format(d);

            Scoreone scoreone = new Scoreone(ND);

            DatabaseReference newScoreRef =
                    FirebaseDatabase.getInstance()
                            .getReference(userID)
                            .push();

            Log.d("SLUGBUG_TEST", "DATE VALUE = " + ND);
            Log.d("SLUGBUG_TEST", "SCORE VALUE = " + scoreone.getNewSlugBug());
            Log.d("SLUGBUG_TEST", "WRITE PATH = " + newScoreRef);

            newScoreRef.setValue(scoreone)
                    .addOnSuccessListener(unused -> {

                        Log.d("SLUGBUG_TEST", "WRITE SUCCESS");

                        // Immediately read the exact record back
                        newScoreRef.get()
                                .addOnSuccessListener(snapshot -> {

                                    Log.d(
                                            "SLUGBUG_TEST",
                                            "READBACK EXISTS = " + snapshot.exists()
                                    );

                                    Log.d(
                                            "SLUGBUG_TEST",
                                            "READBACK VALUE = " + snapshot.getValue()
                                    );

                                    exitTheReward();
                                })
                                .addOnFailureListener(e -> {

                                    Log.e(
                                            "SLUGBUG_TEST",
                                            "READBACK FAILED: " + e.getMessage(),
                                            e
                                    );
                                });
                    })
                    .addOnFailureListener(e -> {

                        Log.e(
                                "SLUGBUG_TEST",
                                "WRITE FAILED: " + e.getMessage(),
                                e
                        );
                    });
        });

    }

            public void exitTheReward() {
                soundPlayer.stop();
                Intent intent = new Intent(slug_bub_reward.this, MainActivity2.class);
                startActivity(intent);
                finish();

            }


            }

























