package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class Bahujan extends AppCompatActivity {
    ImageView bhu;
    FirebaseUser mUser;
    String user_id;
    DatabaseReference mVoteRef, mUserRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahujan);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = mUser.getUid();

        mVoteRef = FirebaseDatabase.getInstance().getReference().child("mVotes");
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);



        bhu=findViewById(R.id.bhu);
        bhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), Fingerprint.class);
                intent.putExtra("party", "BSP");
                startActivity(intent);
                finish();

            }
        });
    }
}