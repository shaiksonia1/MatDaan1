package com.sonia.matdaan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class Bjp extends AppCompatActivity {
    ImageButton bjp1;
    FirebaseUser mUser;
    String user_id;
    DatabaseReference mVoteRef, mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bjp);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = mUser.getUid();

        mVoteRef = FirebaseDatabase.getInstance().getReference().child("mVotes");
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        bjp1 = findViewById(R.id.bjp1);
        bjp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), Fingerprint.class);
                intent.putExtra("party", "BJP");
                startActivity(intent);
                finish();

            }
        });
    }



}
