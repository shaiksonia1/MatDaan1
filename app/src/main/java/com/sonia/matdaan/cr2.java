package com.sonia.matdaan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class cr2 extends AppCompatActivity {
    private EditText mAddress, mVoterid;
    private TextView TextView;
    private TextView upload;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private Uri filePath;
    private static final int GALLERY_PICK = 22;
    private StorageReference mImageStorage;
    private ProgressDialog progressDialog;

    private String userID, link;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference mroot = db.getReference().child("Users").child(create.userID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr2);

        userID = create.userID;

        mAddress = findViewById(R.id.Address);
        mVoterid = findViewById(R.id.voterid);
        TextView = findViewById(R.id.btn);
        upload = findViewById(R.id.uploadPhoto);

        radioGroup = findViewById(R.id.radioGroup1);

        mImageStorage = FirebaseStorage.getInstance().getReference();


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });




        TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(cr2.this);
                progressDialog.setTitle("Please Wait");
                progressDialog.show();

                StorageReference ref = mImageStorage.child("images/"+userID);
                ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });

                String Adress  = mAddress.getText().toString();
                String  voterid = mVoterid.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String profile = radioButton.getText().toString();

                HashMap<String , String> usermap = new HashMap<>();

                usermap.put("Address", Adress);
                usermap.put("voterid" ,voterid);
                usermap.put("name" , getIntent().getStringExtra("name"));
                usermap.put("fathersname" , getIntent().getStringExtra("fname"));
                usermap.put("mothersname" , getIntent().getStringExtra("mname"));
                usermap.put("aadhar", getIntent().getStringExtra("aadhaar"));
                usermap.put("phone" , getIntent().getStringExtra("phone"));
                usermap.put("voted", "no");
                usermap.put("image", "default");
                usermap.put("profile", profile);
                usermap.put("sex", getIntent().getStringExtra("sex"));

                mroot.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        final ProgressDialog progressDialog = new ProgressDialog(cr2.this);
                        progressDialog.setTitle("Please Wait");
                        progressDialog.show();

                        final StorageReference ref = mImageStorage.child("images/"+userID);
                        ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {




                                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        link = task.getResult().toString();
                                        Log.d("LINKK", "onComplete: "+link);
                                        Map map = new HashMap<>();
                                        map.put("image", link);
                                        mroot.updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                progressDialog.dismiss();
                                                Intent intent = new Intent(cr2.this, Dashboard1.class);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });


                            }
                        });



                    }
                });

            }
        });



    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), GALLERY_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);

            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }
}