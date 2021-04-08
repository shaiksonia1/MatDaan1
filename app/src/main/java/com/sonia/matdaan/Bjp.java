package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Bjp extends AppCompatActivity {
    ImageButton bjp1;
    FirebaseUser mUser;
    String user_id;
    DatabaseReference mVoteRef, mUserRef;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bjp);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = mUser.getUid();

        mVoteRef = FirebaseDatabase.getInstance().getReference().child("mVotes");
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);



        bjp1=findViewById(R.id.bjp1);
        bjp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("voted").getValue().toString().equals("no")){
                            mVoteRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String counts = snapshot.child("BJP").getValue().toString();
                                     count = Integer.parseInt(counts);
                                    count++;

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            mVoteRef.child("BJP").setValue(""+count);
                            mUserRef.child("voted").setValue("yes");
                            startActivity(new Intent(Bjp.this,Matdaan.class));
                        }
                        else {
                            Toast.makeText(Bjp.this, "User already voted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}