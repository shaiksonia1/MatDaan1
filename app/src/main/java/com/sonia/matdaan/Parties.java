package com.sonia.matdaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Parties extends AppCompatActivity {
    ImageView inc,bjp,bsp,cpi,ncp,tmc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties);

        inc=findViewById(R.id.inc);
        bjp=findViewById(R.id.bjp);
        bsp=findViewById(R.id.bsp);
        cpi=findViewById(R.id.cpi);
        ncp=findViewById(R.id.ncp);
        tmc=findViewById(R.id.tmc);


        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parties.this,Inc.class));
            }
        });
        bjp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parties.this,Bjp.class));
            }
        });
        bsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parties.this,Bahujan.class));
            }
        });
        cpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parties.this,Cpi.class));
            }
        });
        ncp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parties.this,Ncp.class));
            }
        });
        tmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parties.this,Atic.class));
            }
        });

    }
}