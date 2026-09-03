package com.slbvers1new.slbvers1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.slbvers1new.slbvers1.Scoreone;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class RetrieveDataActivity extends AppCompatActivity {
    List<Scoreone> scoreoneList;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
