package com.sonia.matdaan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {


    ArrayList<News> newsArrayList;


    public NewsListAdapter(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {

       final News news = newsArrayList.get(position);
        holder.titleView.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}


class NewsViewHolder extends RecyclerView.ViewHolder{

    TextView titleView;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

         titleView = itemView.findViewById(R.id.itemTitle);

    }
}
