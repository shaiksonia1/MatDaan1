package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    private DatabaseReference mVoteRef;
    private int bjp;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mVoteRef = FirebaseDatabase.getInstance().getReference().child("mVotes");

        barChart = findViewById(R.id.resultBarChart);

        mVoteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int bjp = (int) snapshot.child("BJP").getChildrenCount();
                int inc = (int) snapshot.child("INC").getChildrenCount();
                int bsp = (int) snapshot.child("BSP").getChildrenCount();
                int cpi = (int) snapshot.child("CPI").getChildrenCount();
                int atic = (int) snapshot.child("ATIC").getChildrenCount();
                int ncp = (int) snapshot.child("NCP").getChildrenCount();

                barEntries = new ArrayList<>();
                barEntries.clear();
                barEntries.add(new BarEntry(0, bjp));
                barEntries.add(new BarEntry(1,inc));
                barEntries.add(new BarEntry(2,bsp));
                barEntries.add(new BarEntry(3,cpi));
                barEntries.add(new BarEntry(4,atic));
                barEntries.add(new BarEntry(5,ncp));

                party = new ArrayList<>();
                party.clear();
                party.add("BJP");
                party.add("INC");
                party.add("BSP");
                party.add("CPI");
                party.add("ATIC");
                party.add("NCP");

                barDataSet = new BarDataSet(barEntries, "Votes");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                barData = new BarData(barDataSet);
                barChart.setData(barData);
                barChart.getDescription().setEnabled(false);
                barChart.getLegend().setEnabled(false);
                barChart.setScaleEnabled(false);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(party));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(false);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(party.size());
                xAxis.setTextSize(20f);
                barChart.animateY(2000);
                barChart.invalidate();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("BJPP", "onCreate: "+bjp);




    }
}