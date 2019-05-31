package com.curtcaldwell.nikecodechallenge;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.curtcaldwell.nikecodechallenge.model.Result;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    List<Result> resultList = new ArrayList<>();
    Context context;
    MainActivity.AlbumClickListener listener;


    public FeedAdapter(Context c, MainActivity.AlbumClickListener l) {
        context = c;
        listener = l;


    }


    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.result_item, viewGroup, false);
        FeedViewHolder vh = new FeedViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder feedViewHolder, int i) {
        feedViewHolder.setData(resultList.get(i), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAlbumClicked(resultList.get(feedViewHolder.getAdapterPosition()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void updateList(List<Result> list) {
        resultList.addAll(list);
        notifyDataSetChanged();
    }
}
