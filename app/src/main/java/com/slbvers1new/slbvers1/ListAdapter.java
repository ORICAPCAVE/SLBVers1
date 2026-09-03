package com.slbvers1new.slbvers1;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.slbvers1new.slbvers1.Scoreone;
import com.slbvers1new.slbvers1.SlugBugScore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slbvers1new.slbvers1.Eventof;

import java.util.List;

public class ListAdapter extends ArrayAdapter {


    private Activity mContext;
    private AdapterView.OnItemClickListener onItemClickListener;

    List<Scoreone> scoreoneList;
    List<String> scoreKeyList;
    Button btnDialogDelete, btnDialogCancel;
    SlugBugScore slugBugScore;
    FragmentManager manager;
    ImageView imageView4;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance()
            .getUid());

    public ListAdapter(
            Activity mContext,
            List<Scoreone> scoreoneList,
            List<String> scoreKeyList
    ) {
        super(mContext, R.layout.item_list, scoreoneList);

        this.mContext = mContext;
        this.scoreoneList = scoreoneList;
        this.scoreKeyList = scoreKeyList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item_list, parent, false);

        //TextView slugBugE = listItemView.findViewById(R.id.SlugBugE);
        TextView slugBugC = listItemView.findViewById(R.id.SlugBugC);
        // Eventof eventof = eventKeyList.get(position);
        Scoreone scoreone = scoreoneList.get(position);
        String scoreKey = scoreKeyList.get(position);
        slugBugC.setText(scoreone.getNewSlugBug());

        slugBugC.setOnClickListener(v -> {

            AlertDialog dialog =
                    createDialog(scoreone, scoreKey);

            dialog.show();
        });
        return listItemView;
    }

    AlertDialog createDialog(Scoreone scoreone, String scoreKey) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(mContext);

        builder.setMessage(
                "Do you want to Delete this Score?"
        );

        builder.setPositiveButton(
                "Yes",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            android.content.DialogInterface dialog,
                            int which
                    ) {

                        myRef.child(scoreKey)
                                .removeValue()
                                .addOnSuccessListener(unused -> {

                                    int index = scoreKeyList.indexOf(scoreKey);

                                    if (index >= 0) {
                                        scoreKeyList.remove(index);
                                        scoreoneList.remove(index);
                                    }

                                    notifyDataSetChanged();

                                    Toast.makeText(
                                            mContext,
                                            "Score Deleted",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                });
                    }
                }
        );

        builder.setNegativeButton(
                "No",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            android.content.DialogInterface dialog,
                            int which
                    ) {

                        Toast.makeText(
                                mContext,
                                "Clicked No",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        return builder.create();
    }
}