package com.curtcaldwell.nikecodechallenge;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.curtcaldwell.nikecodechallenge.model.Genre;
import com.curtcaldwell.nikecodechallenge.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailsActivity extends AppCompatActivity {

    private Result result;
    private TextView albumText;
    private TextView artistText;
    private TextView genreText;
    private TextView releaseDateText;
    private TextView copyWriteText;
    private ImageView albumArtImage;
    private Button playButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_detail_layout);
        result = getIntent().getParcelableExtra("result");

        albumText = findViewById(R.id.album_text);
        artistText = findViewById(R.id.artist_text);
        genreText = findViewById(R.id.genre_text);
        releaseDateText = findViewById(R.id.release_date_text);
        copyWriteText = findViewById(R.id.copyright_text);
        albumArtImage = findViewById(R.id.album_art_image_view);
        playButton = findViewById(R.id.play_button);

        albumText.setText(result.getName());
        artistText.setText(result.getArtistName());
        String genreName = "";
        List<Genre> genres = new ArrayList<>();
        genres.addAll(result.getGenres());
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i) != null && genres.get(i).getName() != null) {
                if (i != genres.size() - 1) {
                    genreName = genreName + genres.get(i).getName();
                } else
                    genreName = genreName + ", " + genres.get(i).getName();
            }
        }

        genreText.setText(genreName);
        releaseDateText.setText(getString(R.string.release_date, result.getReleaseDate()));
        copyWriteText.setText(result.getCopyright());
        Picasso.get().load(result.getArtworkUrl100()).resize(700, 700).centerCrop().into(albumArtImage);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAppInstalled(getApplicationContext(), "com.apple.android.music")) {
                    Intent i;
                    PackageManager packageManager = getPackageManager();
                    i = packageManager.getLaunchIntentForPackage("com.apple.android.music");
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);

                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getArtistUrl()));
                    startActivity(browserIntent);
                }
            }
        });

    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
