package com.curtcaldwell.nikecodechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.curtcaldwell.nikecodechallenge.model.Result;
import com.curtcaldwell.nikecodechallenge.model.Rss;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String BASE_URL = "https://rss.itunes.apple.com/api/v1/";

    private FeedAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new FeedAdapter(this, new AlbumClickListener() {
            @Override
            public void onAlbumClicked(Result result) {

                startAlbumDetailsActivity(result);

            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<Rss> call = feedApi.getFeed();

        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                List<Result> results = new ArrayList<>(response.body().getFeed().getResults());
                adapter.updateList(results);

            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {

                Log.d("On failure: ", t.getMessage());

            }
        });

    }

    public interface AlbumClickListener {
        void onAlbumClicked(Result result);
    }

    public void startAlbumDetailsActivity(Result result) {
        Intent intent = new Intent(MainActivity.this, AlbumDetailsActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
