package com.sonia.matdaan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard1 extends AppCompatActivity{
ImageView evm;
ImageView cmp;
ImageView ab;
ImageView mAnnouncementBtn;
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);

        evm=findViewById(R.id.evm);
        evm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Parties.class));
                //Toast.makeText(Dashboard.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        cmp=findViewById(R.id.cmp);
        cmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Complaint.class));
            }
        });

        mAnnouncementBtn = findViewById(R.id.announcementBtn);
        mAnnouncementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Announcements.class));
            }
        });
        ab = findViewById(R.id.ab);
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), About_us.class));
            }
        });
//        ab.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Uri uri = Uri.parse("http://google.com/");
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });




        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        finish();
                        startActivity(getIntent());
                        finish();
                        break;
//
//                    case R.id.home:
//                        startActivity(new Intent(Userinfo.this,Userinfo.class));
//                        break;

                    case R.id.user:
                        startActivity(new Intent(Dashboard1.this,Userinfo.class));
                        break;

                    case R.id.announcement:
                        startActivity(new Intent(Dashboard1.this,Announcements.class));
                        break;
                }
                return true;
            }
        });

    }
}
