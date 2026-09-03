package com.slbvers1new.slbvers1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.UserHandle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;


import com.slbvers1new.slbvers1.slug_bub_reward;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;


public class Play_Bug extends AppCompatActivity {
    private static int ukupanbroj;


    Button home5, Buggy;

    private static final int SPEECH_REQUEST_CODE = 0;
    private SpeechRecognizer speechRecognizer;
    AlertDialog.Builder alertSpeechDialog;
    AlertDialog alertDialog;
    public static final Integer RecordAudioRequestCode = 1;
    public Scanner sc;
    private TextView EndItPlease;
    private TextView textView5;
    private TextView PlayingYay;
    private ImageView imageView;

    public String bug_get;
    private slug_bub_reward slugBubReward;

    //Room<MyState> room;
    // Room.MessageHandler<Message> adapter;
    // final HashMap<String, UserHandle> users = new HashMap<>();
    // final SparseArray<Message> messages = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_play__bug);

        View root = findViewById(R.id.playBugRoot);

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

        textView5 = findViewById(R.id.textView5);
        PlayingYay = findViewById(R.id.PlayingYay);
        imageView = findViewById(R.id.imageView);
        EndItPlease = findViewById(R.id.EndItPlease);

        // existing speech code continues...
        //OpenServerConnection();

        // speech recognition code area.
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent sppechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        sppechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        sppechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(Play_Bug.this).inflate(R.layout.alertcustom,
                        viewGroup, false);
                alertSpeechDialog = new AlertDialog.Builder(Play_Bug.this);
                alertSpeechDialog.setMessage("Listening for Buggy's!");
                alertSpeechDialog.setView(dialogView);
                alertDialog = alertSpeechDialog.create();
                alertDialog.show();

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                imageView.setImageResource(R.drawable.baseline_mic_24);
                ArrayList<String> arrayList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String text = arrayList.get(0);
                bug_get = (arrayList.get(0));
                PlayingYay.setText(arrayList.get(0));
                alertDialog.dismiss();
                //Borrowed code in here. which made the bug_get increment.
                String trimmed = text.trim();
                int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
                // boolean check of the string values in the array.
                if ((bug_get).equalsIgnoreCase("bug") ||
                        ((bug_get).equalsIgnoreCase("buggy"))) {
                    ukupanbroj += words;
                    textView5.setText("" + ukupanbroj);
                    // inform players of players score.
                }
                if ((ukupanbroj == 8)) {
                    textView5.setText("8");
                    speechRecognizer.stopListening();
                    Toast.makeText(Play_Bug.this, "Yay! you got a slug bug!", Toast.LENGTH_LONG).show();
                    // Record the score to DB

                    // Inform all players of the Slug bug Score
                    // play a visual with music.
                    Intent intent = new Intent(Play_Bug.this, slug_bub_reward.class);
                    startActivity(intent);

                    // reset the score to 0

                    Toast.makeText(Play_Bug.this, "Play Again!", Toast.LENGTH_LONG).show();
                    ukupanbroj = 0;
                    //textView5.setText("" + ukupanbroj);
                    speechRecognizer.stopListening();
                    speechRecognizer.destroy();

                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }


        });
        // Where the image view is getting the controls.

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // if (event.getAction() == MotionEvent.ACTION_UP) {
                //   speechRecognizer.stopListening();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageView.setImageResource(R.drawable.baseline_mic_24);
                    speechRecognizer.startListening(sppechIntent);
                }
                return false;
            }
        });


        // stops the process of the game.
        EndItPlease = findViewById(R.id.EndItPlease);
        EndItPlease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                Intent intent = new Intent(Play_Bug.this, Play_Bug.class);
                startActivity(intent);
                Toast.makeText(Play_Bug.this, "Okay! Lets find some more Buggy's", Toast.LENGTH_LONG).show();

            }
        });
        // the user can head to the home page from here.
        home5 = (Button) findViewById(R.id.home5);
        home5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play_Bug.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        // add a buggy because its to noisy.
        Buggy = (Button) findViewById(R.id.Buggy);
        Buggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ukupanbroj < 8) {
                    ++ukupanbroj;
                    textView5.setText("" + ukupanbroj);
                }
                if ((ukupanbroj == 8)) {
                    textView5.setText("8");
                    speechRecognizer.stopListening();
                    Toast.makeText(Play_Bug.this, "Yay! you got a slug bug!", Toast.LENGTH_LONG).show();
                    // Record the score to DB

                    // Inform all players of the Slug bug Score
                    // play a visual with music.
                    Intent intent = new Intent(Play_Bug.this, slug_bub_reward.class);
                    startActivity(intent);

                    // reset the score to 0

                    Toast.makeText(Play_Bug.this, "Play Again!", Toast.LENGTH_LONG).show();
                    ukupanbroj = 0;
                    //textView5.setText("" + ukupanbroj);
                    speechRecognizer.stopListening();
                    speechRecognizer.destroy();
                }
            }

        });
    }
}
























