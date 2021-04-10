package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Userinfo extends AppCompatActivity {

    private TextView name, address, phone, father, mother, voterid, aadhaar, status;
    private CircleImageView image;
    private DatabaseReference databaseReference;
    private String userID;
    private FirebaseUser firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        image = findViewById(R.id.profile_image);

        name = findViewById(R.id.profile_name);
        address = findViewById(R.id.profile_address);
        phone = findViewById(R.id.profilenumber);
        father = findViewById(R.id.profilefathername);
        mother = findViewById(R.id.profilemothername);
        voterid = findViewById(R.id.profilevoterid);
        aadhaar = findViewById(R.id.profileaadhaar);
        status = findViewById(R.id.profilevotingstatus);

        firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseAuth.getUid();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText("Name: "+snapshot.child("name").getValue().toString());
                address.setText(snapshot.child("Address").getValue().toString());
                phone.setText(snapshot.child("phone").getValue().toString());
                father.setText(snapshot.child("fathersname").getValue().toString());
                mother.setText(snapshot.child("mothersname").getValue().toString());
                voterid.setText(snapshot.child("voterid").getValue().toString());
                aadhaar.setText(snapshot.child("aadhar").getValue().toString());
                status.setText(snapshot.child("voted").getValue().toString());

                String url = snapshot.child("image").toString();

                Picasso.get().load(url).placeholder(R.drawable.ic_user).into(image);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }
}