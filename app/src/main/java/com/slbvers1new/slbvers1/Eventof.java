package com.slbvers1new.slbvers1;

import com.slbvers1new.slbvers1.Scoreone;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Eventof {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    public Eventof() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Scoreone.class.getSimpleName());
        firebaseAuth = FirebaseAuth.getInstance();
        String userID = firebaseAuth.getUid();

    }

}