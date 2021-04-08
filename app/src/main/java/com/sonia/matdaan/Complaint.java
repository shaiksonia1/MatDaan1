package com.sonia.matdaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Complaint extends AppCompatActivity {
Button locality;
Button workers;
Button uti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        Button buttonLogin=findViewById(R.id.locality);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Complaint.this, Locality.class);
                startActivity(myIntent);

            }
        });
        Button ButtonLogin=findViewById(R.id.workers);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Workers.class));
            }
        });
        Button ButtonLogIn=findViewById(R.id.uti);
        ButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Utility.class));
            }
        });
        Button ButtonLogIN=findViewById(R.id.animal);
        ButtonLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Animal.class));
            }
        });


        Button ButtonLOgIn=findViewById(R.id.other);
        ButtonLOgIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Others.class));
            }
        });


    }
    }
