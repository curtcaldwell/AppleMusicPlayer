package com.curtcaldwell.nikecodechallenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.curtcaldwell.nikecodechallenge.model.Result;
import com.squareup.picasso.Picasso;

public class FeedViewHolder extends RecyclerView.ViewHolder {
    TextView artistNameTextView;
    TextView albumNameTextView;
    ImageView albumArtImageView;
    View root;


    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        artistNameTextView = itemView.findViewById(R.id.artist_name);
        albumNameTextView = itemView.findViewById(R.id.album_name);
        albumArtImageView = itemView.findViewById(R.id.album_art);
        root = itemView;
    }

    public void setData(Result result, View.OnClickListener listener) {
        artistNameTextView.setText(result.getArtistName());
        albumNameTextView.setText(result.getName());
        Picasso.get().load(result.getArtworkUrl100()).into(albumArtImageView);
        root.setOnClickListener(listener);


    }
}
