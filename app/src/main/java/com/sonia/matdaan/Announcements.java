package com.sonia.matdaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Announcements extends AppCompatActivity {

    RecyclerView mView;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Announcement");

        mView = findViewById(R.id.announcementView);
        mView.setHasFixedSize(true);
        mView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AnnouncementModel> options = new FirebaseRecyclerOptions.Builder<AnnouncementModel>()
                .setQuery(mDatabase, AnnouncementModel.class)

                .build();

        FirebaseRecyclerAdapter<AnnouncementModel, AnnouncementViewHolder> adapter = new FirebaseRecyclerAdapter<AnnouncementModel, AnnouncementViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position, @NonNull AnnouncementModel model) {

                String heading = model.heading;
                String headingg = model.getHeading();
                Log.d("ANN", "onBindViewHolder: "+model.heading);
                Log.d("ANN", "onBindViewHolder: "+model.getHeading());
                holder.mHeading.setText(headingg);

                final String id = getRef(position).getKey();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ViewAnnouncement.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_announcement, parent, false);
                return new AnnouncementViewHolder(view);
            }
        };

        mView.setAdapter(adapter);
        adapter.startListening();

    }

    private static class AnnouncementViewHolder extends RecyclerView.ViewHolder {

        TextView mHeading;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);

            mHeading = itemView.findViewById(R.id.annHeading);
        }
    }
}