package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAnnouncement extends AppCompatActivity {

    private String id;
    private TextView heading, content;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_announcement);

        id = getIntent().getStringExtra("id");

        heading = findViewById(R.id.announcementHeading);
        content = findViewById(R.id.announcementContent);

        ref = FirebaseDatabase.getInstance().getReference().child("Announcement").child(id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                heading.setText(snapshot.child("heading").getValue().toString());
                content.setText(snapshot.child("content").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}