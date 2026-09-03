package com.slbvers1new.slbvers1;
import android.app.Dialog;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Scoreone {

    private String newSlugBug;

    public Scoreone() {
        // Required by Firebase
    }

    public Scoreone(String newSlugBug) {
        this.newSlugBug = newSlugBug;
    }

    public String getNewSlugBug() {
        return newSlugBug;
    }

    public void setNewSlugBug(String newSlugBug) {
        this.newSlugBug = newSlugBug;
    }
}