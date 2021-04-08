package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class create extends AppCompatActivity {

    //variable
    private EditText mName, mFathersname, mMothersname, mPhone, mAadhar ;
    private Button button;
    RadioButton radiosexMale,radiosexFemale,radiosexothers;
    DatabaseReference DatabaseReference;
    String sex = "";
    FirebaseAuth FirebaseAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    TextView tvNext;
    private FirebaseDatabase mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public static  String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        // hooks to all xml element int activity_sign_up.xml
        mName = findViewById(R.id.reg_Name);
        mFathersname = findViewById(R.id.Fathersname);
        mMothersname = findViewById(R.id.mothersname);
        mAadhar=findViewById(R.id.aadhar);
        mPhone = findViewById(R.id.phone);
        tvNext = findViewById(R.id.button);
        radiosexMale = findViewById(R.id.radioButtonMale);
        radiosexFemale = findViewById(R.id.radioButtonFemale);
        radiosexothers = findViewById(R.id.radioButtonOthers);

        DatabaseReference = FirebaseDatabase.getInstance().getReference("gender");
        FirebaseAuth = FirebaseAuth.getInstance();
        userID = FirebaseAuth.getCurrentUser().getUid();

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String  name = mName.getText().toString();
                final String Fathersname = mFathersname.getText().toString();
                final String Mothersname = mMothersname.getText().toString();
                final String Aadhar = mAadhar.getText().toString();
                final String Phone = mPhone.getText().toString();

                if(radiosexMale.isChecked()){
                    sex="Male";
                    if(radiosexFemale.isChecked()){
                        sex ="Female";
                        if(radiosexothers.isChecked()){
                            sex ="others";

                        }

                    }
                }

//                HashMap<String , String>userMap = new HashMap<>();
//
//
//
//                userMap.put("name" , name);
//                userMap.put("fathersname" , Fathersname);
//                userMap.put("mothersname" , Mothersname);
//                userMap.put("aadhar", Aadhar);
//                userMap.put("phone" , Phone);
//                userMap.put("voted", "no");
//                root.child(userID).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
                            Intent intent = new Intent(create.this, cr2.class);
                            intent.putExtra("name", name);
                            intent.putExtra("fname", Fathersname);
                            intent.putExtra("mname", Mothersname);
                            intent.putExtra("phone", Phone);
                            intent.putExtra("aadhaar", Aadhar);
                            startActivity(intent);
                        }
//                    }
//                });









                });
    }


        }




