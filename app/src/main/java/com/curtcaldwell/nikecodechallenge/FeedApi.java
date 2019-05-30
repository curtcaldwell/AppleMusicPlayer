package com.curtcaldwell.nikecodechallenge;

import com.curtcaldwell.nikecodechallenge.model.Feed;
import com.curtcaldwell.nikecodechallenge.model.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedApi {

    String BASE_URL = "https://rss.itunes.apple.com/api/v1/";

    @GET("us/apple-music/top-albums/all/25/explicit.rss")
    Call<Rss> getRss();
}
