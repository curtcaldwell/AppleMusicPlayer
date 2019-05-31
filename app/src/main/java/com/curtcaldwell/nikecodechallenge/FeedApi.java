package com.curtcaldwell.nikecodechallenge;

import com.curtcaldwell.nikecodechallenge.model.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedApi {


    @GET("us/apple-music/top-albums/all/25/explicit.json")
    Call<Rss> getFeed();
}
