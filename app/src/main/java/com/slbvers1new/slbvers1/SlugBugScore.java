package com.slbvers1new.slbvers1;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;



import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.support.v4.app.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.slbvers1new.slbvers1.Eventof;
import com.slbvers1new.slbvers1.MainActivity2;
import com.slbvers1new.slbvers1.Scoreone;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SlugBugScore extends AppCompatActivity {
    Button button2, button3, btnDialogDelete, btnDialogCancel;
    Context context;
    public MediaPlayer soundPlayer;
    ListView BuggyScoresHere;
    List<Scoreone> scoreoneList;
    List<String> scoreKeyList;
    List<Eventof> eventKeyList;
    ArrayList<String> arrayList = new ArrayList<>();
    String key;
    String userID;
    static String TAG;
    FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slug_bug_score);
        //FragmentManager fragmentManager = getSupportFragmentManager();
       // FragmentTransaction ft = fragmentManager.beginTransaction();
       // ft.addToBackStack("SlugBugCount");
        //ft.commit();
        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.slugbugreward);
        BuggyScoresHere = findViewById(R.id.BuggyScoresHere);
        TextView SlugBugCount = findViewById(R.id.SlugBugCount);
       /* ListView lv = findViewById(R.id.BuggyScoresHere);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(arrayAdapter);*/


        scoreoneList = new ArrayList<>();
        scoreKeyList = new ArrayList<>();
        eventKeyList = new ArrayList<>();
        soundPlayer.stop();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlugBugScore.this, MainActivity2.class);
                startActivity(intent);


            }


        });
        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(v -> {

            Log.d("SCORE_TEST", "GET SCORES CLICKED");
            Log.d("SCORE_TEST", "USER ID = " + userID);

            DatabaseReference myRef =
                    FirebaseDatabase.getInstance()
                            .getReference(userID);

            Log.d("SCORE_TEST", "READ PATH = " + myRef);

            myRef.addListenerForSingleValueEvent(
                    new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.d(
                                    "SCORE_TEST",
                                    "SNAPSHOT EXISTS = " + dataSnapshot.exists()
                            );

                            Log.d(
                                    "SCORE_TEST",
                                    "CHILD COUNT = " + dataSnapshot.getChildrenCount()
                            );

                            scoreoneList.clear();
                            scoreKeyList.clear();

                            for (DataSnapshot ds :
                                    dataSnapshot.getChildren()) {

                                Log.d(
                                        "SCORE_TEST",
                                        "CHILD KEY = " + ds.getKey()
                                );

                                Log.d(
                                        "SCORE_TEST",
                                        "CHILD VALUE = " + ds.getValue()
                                );

                                Scoreone scoreone =
                                        ds.getValue(Scoreone.class);

                                if (scoreone != null) {

                                    Log.d(
                                            "SCORE_TEST",
                                            "SCORE = " +
                                                    scoreone.getNewSlugBug()
                                    );

                                    scoreoneList.add(scoreone);
                                    scoreKeyList.add(ds.getKey());
                                }
                            }

                            ListAdapter adapter =
                                    new ListAdapter(
                                            SlugBugScore.this,
                                            scoreoneList,
                                            scoreKeyList
                                    );

                            BuggyScoresHere.setAdapter(adapter);

                            SlugBugCount.setText(
                                    "Slug Bugs!  " +
                                            scoreoneList.size()
                            );
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                            Log.e(
                                    "SCORE_TEST",
                                    "READ FAILED: " +
                                            error.getMessage(),
                                    error.toException()
                            );
                        }
                    }
            );
        });

    }



    }

































